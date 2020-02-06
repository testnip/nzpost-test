package nz.co.nzpost.automation.external.saucelabs;

import com.codeborne.selenide.WebDriverRunner;
import com.saucelabs.saucerest.SauceREST;
import cucumber.api.Scenario;
import nz.co.nzpost.automation.external.ExternalClient;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

@Component
public class SaucelabsClient implements ExternalClient {

  private static final List<String> BRANCH_NAMES = Arrays.asList("dev", "sit", "master");
  private final SauceREST sauceRest;
  private final String build;
  private final String branchName;

  @Autowired
  public SaucelabsClient(SaucelabsProperties saucelabsProperties, Environment environment) {
    sauceRest = new SauceREST(saucelabsProperties.getUsername(), saucelabsProperties.getAccessKey());

    // build number
    DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy HH-mm-ss");
    build = format("Web Replatform Skywalkers %s", formatter.print(DateTime.now()));

    // branch name
    branchName = getBranchName(environment);

  }

  /**
   * Get branch name from the active profiles
   *
   * @param environment
   * @return
   */
  private String getBranchName(Environment environment) {
    return Arrays.stream(environment.getActiveProfiles())
      .filter(activeProfile -> BRANCH_NAMES.contains(activeProfile.toLowerCase()))
      .findFirst()
      .orElse("");
  }

  /**
   * We are using saucelabs api to update the job name with the scenario name. Refer to https://wiki.saucelabs.com/display/DOCS/Job+Methods#JobMethods-UpdateJob.
   *
   * @param scenario
   */
  @Override
  public void updateSession(Scenario scenario) {
    sauceRest.updateJobInfo(getJobId(), getJobInfo(scenario));
  }

  private String getJobId() {
    RemoteWebDriver webDriver = (RemoteWebDriver) WebDriverRunner.getWebDriver();
    return webDriver.getSessionId().toString();
  }

  private Map<String, Object> getJobInfo(Scenario scenario) {
    final Map<String, Object> jobInfo = new HashMap();
    jobInfo.put("name", format("%s - %s", scenario.getName(), branchName));
    jobInfo.put("passed", !scenario.isFailed());
    jobInfo.put("tags", scenario.getSourceTagNames());
    jobInfo.put("build", format("%s - %s", build, branchName));
    return jobInfo;
  }

}