package nz.co.nzpost.automation.external;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public interface ExternalDriverManager {
  RemoteWebDriver getDriver();
  RemoteWebDriver getChromeDriver();
}
