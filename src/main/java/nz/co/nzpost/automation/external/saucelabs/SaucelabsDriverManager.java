package nz.co.nzpost.automation.external.saucelabs;

import com.codeborne.selenide.WebDriverRunner;
import nz.co.nzpost.automation.domain.GeolocationDisabled;
import nz.co.nzpost.automation.external.ExternalDriverManager;
import org.apache.logging.log4j.util.Strings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.String.format;

@Component
public class SaucelabsDriverManager implements ExternalDriverManager {

  private final SaucelabsProperties saucelabsProperties;

  @Autowired
  public SaucelabsDriverManager(SaucelabsProperties saucelabsProperties) {
    this.saucelabsProperties = saucelabsProperties;
  }


  @Override
  public RemoteWebDriver getDriver() {
    try {
      // browser capabilities
      final DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setCapability("screenResolution", saucelabsProperties.getResolution());
      capabilities.setCapability("platform", saucelabsProperties.getPlatform());

      if (Strings.isNotBlank(saucelabsProperties.getBrowserName()) &&
        Strings.isNotBlank(saucelabsProperties.getBrowserVersion())) {
        capabilities.setCapability("browserName", saucelabsProperties.getBrowserName());
        capabilities.setCapability("version", saucelabsProperties.getBrowserVersion());
      }

      // saucelabs
      final String saucelabsUrl = format("https://%s:%s@%s/wd/hub", saucelabsProperties.getUsername(), saucelabsProperties.getAccessKey(), saucelabsProperties.getDomain());
      return new RemoteWebDriver(new URL(saucelabsUrl), capabilities);
    } catch (MalformedURLException e) {
      throw new IllegalStateException("Failed to create external driver!", e);
    }
  }

  @Override
  public RemoteWebDriver getChromeDriver() {
    try {
      // browser capabilities
      final DesiredCapabilities capabilities = new DesiredCapabilities();
      GeolocationDisabled prefs = new GeolocationDisabled();

      // Set the notification setting it will override the default setting
      prefs.put("profile.default_content_setting_values.geolocation", 2);


      // Create object of ChromeOption class
      ChromeOptions options = new ChromeOptions();

      // Set the experimental option
      options.setExperimentalOption("prefs", prefs);

      // pass the options object in Chrome driver

     capabilities.setCapability(ChromeOptions.CAPABILITY,options);
      capabilities.setCapability("screenResolution", saucelabsProperties.getResolution());
      capabilities.setCapability("platform", saucelabsProperties.getPlatform());

      if (Strings.isNotBlank(saucelabsProperties.getBrowserName()) &&
        Strings.isNotBlank(saucelabsProperties.getBrowserVersion())) {
        capabilities.setCapability("browserName", saucelabsProperties.getBrowserName());
        capabilities.setCapability("version", saucelabsProperties.getBrowserVersion());
      }

      // saucelabs
      final String saucelabsUrl = format("https://%s:%s@%s/wd/hub", saucelabsProperties.getUsername(), saucelabsProperties.getAccessKey(), saucelabsProperties.getDomain());
      return new RemoteWebDriver(new URL(saucelabsUrl), capabilities);
    } catch (MalformedURLException e) {
      throw new IllegalStateException("Failed to create external driver!", e);
    }
  }
}
