package nz.co.nzpost.automation.screenshot.ashot;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import nz.co.nzpost.automation.external.CloudProperties;
import nz.co.nzpost.automation.io.FileReader;
import nz.co.nzpost.automation.screenshot.ScreenshotTaker;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@Component
@Scope("cucumber-glue")
public class ScreenshotTakerAshot implements ScreenshotTaker {

  private final CloudProperties cloudProperties;
  private final FileReader fileReader;

  @Autowired
  public ScreenshotTakerAshot(CloudProperties cloudProperties, FileReader fileReader) throws InterruptedException, IOException {
    this.cloudProperties = cloudProperties;
    this.fileReader = fileReader;
  }

  // FIXME: stop this from initial load, this should only be invoked when starting a scenario
  private void loadJQuery() throws InterruptedException, IOException {
    // load jquery for ashot
    InputStream jquerifyInputStream = fileReader.read("jquery/jquerify.js");
    String jquerify = IOUtils.toString(jquerifyInputStream);
    WebDriverRunner.getWebDriver().manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    Selenide.executeJavaScript(jquerify);
    Thread.sleep(2000);
  }

  @Override
  public BufferedImage takeScreenShot(WebElement logoImage) {
    // attempt to load jquery lib which is used to take the screenshots
    try {
      loadJQuery();
    } catch (Exception e) {
      e.printStackTrace();
    }

    Screenshot logoImageScreenshot = new AShot().coordsProvider(new WebDriverCoordsProvider()).shootingStrategy(getScaling()).takeScreenshot(WebDriverRunner.getWebDriver(), logoImage);
    return logoImageScreenshot.getImage();
  }

  private ShootingStrategy getScaling() {
    return ShootingStrategies.scaling(cloudProperties.isEnabled() ? 1 : 2);
  }
}
