package nz.co.nzpost.automation.browser;

import com.codeborne.selenide.WebDriverRunner;
import nz.co.nzpost.automation.domain.GeolocationDisabled;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

@Component
public class Browser {

  public void sendKeysToInputAtSpeed(String string, WebElement webElement, int milliSeconds) throws InterruptedException {
    for (char character : string.toCharArray()) {
      webElement.sendKeys(String.valueOf(character));
      Thread.sleep(milliSeconds);
    }
  }

  public WebDriver chromeGeolocationDisabled() {


    GeolocationDisabled prefs = new GeolocationDisabled();

    // Set the notification setting it will override the default setting
    prefs.put("profile.default_content_setting_values.geolocation", 2);

    // Create object of ChromeOption class
    ChromeOptions options = new ChromeOptions();

    // Set the experimental option
    options.setExperimentalOption("prefs", prefs);

    // pass the options object in Chrome driver

    WebDriver driver = new ChromeDriver(options);

    WebDriverRunner.setWebDriver(driver);
    return driver;
  }
}
