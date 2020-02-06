package nz.co.nzpost.automation.screenshot;

import org.openqa.selenium.WebElement;

import java.awt.image.BufferedImage;

public interface ScreenshotTaker {
  BufferedImage takeScreenShot(WebElement logoImage);
}
