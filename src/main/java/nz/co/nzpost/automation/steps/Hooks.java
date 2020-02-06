package nz.co.nzpost.automation.steps;

import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import nz.co.nzpost.automation.annotations.AfterAll;
import nz.co.nzpost.automation.annotations.BeforeAll;
import nz.co.nzpost.automation.browser.Browser;
import nz.co.nzpost.automation.configuration.AutomationConfiguration;
import nz.co.nzpost.automation.external.CloudProperties;
import nz.co.nzpost.automation.external.ExternalClient;
import nz.co.nzpost.automation.external.ExternalDriverManager;
import nz.co.nzpost.automation.report.ReportGenerator;
import org.junit.runner.RunWith;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.codeborne.selenide.Selenide.close;
import static org.openqa.selenium.OutputType.BYTES;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AutomationConfiguration.class)
@SpringBootTest
public class Hooks {

  private static final String API_TAG = "@api";
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  private final ExternalClient externalClient;
  private final ReportGenerator reportGenerator;
  private final ExternalDriverManager externalDriverManager;
  private final CloudProperties cloudProperties;
  private final Browser browser;

  @Autowired
  public Hooks(CloudProperties cloudProperties, ExternalClient externalClient, ReportGenerator reportGenerator, ExternalDriverManager externalDriverManager, Browser browser) {
    this.cloudProperties = cloudProperties;
    this.externalClient = externalClient;
    this.reportGenerator = reportGenerator;
    this.externalDriverManager = externalDriverManager;
    this.browser = browser;
  }

  @BeforeAll
  public void beforeAllScenarios() {
    log.info("-------------- executing BeforeAll hook ----------------");
  }

  @Before("@automation")
  public void beforeScenario(Scenario scenario) {
    if (isUIScenario(scenario)) {
      if (cloudProperties.isEnabled()) {
        WebDriverRunner.setWebDriver(externalDriverManager.getDriver());
      }
      WebDriverRunner.getWebDriver().manage().window().fullscreen();
    }
  }

  @Before("@geolocation")
  public void beforeGelocation(Scenario scenario) {
    if (isUIScenario(scenario)) {
      if (cloudProperties.isEnabled()) {
        WebDriverRunner.setWebDriver(externalDriverManager.getChromeDriver());
      } else {
        browser.chromeGeolocationDisabled().manage().window().fullscreen();
      }
    }
  }

  @Before("@submit")
  public void beforeSubmit(Scenario scenario) {
    if (isUIScenario(scenario)) {
      JavascriptExecutor je = (JavascriptExecutor) WebDriverRunner.getWebDriver();
      je.executeScript("window.__P4U_AUTOMATION_TEST__=true;");
      //String testVal = je.executeScript("return window.__P4U_AUTOMATION_TEST__;").toString();

      //if (cloudProperties.isEnabled()) {
       // WebDriverRunner.setWebDriver(externalDriverManager.getChromeDriver());
      //} else {
       // browser.chromeGeolocationDisabled().manage().window().fullscreen();
      }
    }


  @After
  public void afterScenario(Scenario scenario) {
    if (!isUIScenario(scenario)) {
      return;
    }

    try {
      if (cloudProperties.isEnabled()) {
        externalClient.updateSession(scenario);
      }

      if (scenario.isFailed()) {
        final TakesScreenshot takesScreenshot = (TakesScreenshot) WebDriverRunner.getWebDriver();
        final byte[] screenshot = takesScreenshot.getScreenshotAs(BYTES);
        scenario.embed(screenshot, "image/png");
      }
    } finally {
      close();
    }
  }

  @AfterAll
  public void afterAllScenarios() {
    log.info("-------------- executing AfterAll hook ----------------");
    reportGenerator.generate();
  }

  private boolean isUIScenario(Scenario scenario) {
    return !scenario.getSourceTagNames().contains(API_TAG);
  }
}
