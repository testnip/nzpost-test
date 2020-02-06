package nz.co.nzpost.automation.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.co.nzpost.automation.io.FilePathBuilder;
import nz.co.nzpost.automation.io.ImageReader;
import nz.co.nzpost.automation.page.AddressSearchPage;
import nz.co.nzpost.automation.screenshot.ScreenshotTaker;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import java.awt.image.BufferedImage;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class AddressSearchMapSteps {

  private final FilePathBuilder filePathBuilder;
  private final ImageReader imageReader;
  private final AddressSearchPage addressSearchPage;
  private final ScreenshotTaker screenshotTaker;

  @Autowired
  public AddressSearchMapSteps(FilePathBuilder filePathBuilder, ImageReader imageReader, AddressSearchPage addressSearchPage, ScreenshotTaker screenshotTaker) {
    this.filePathBuilder = filePathBuilder;
    this.imageReader = imageReader;
    this.addressSearchPage = addressSearchPage;
    this.screenshotTaker = screenshotTaker;
  }

  @Then("^User should see the correct map for \"([^\"]*)\"$")
  public void userShouldSeeTheCorrectMapFor(String address) throws Throwable {
    Thread.sleep(20000);

    // capture actual image
    BufferedImage actualImage = addressSearchPage.getMapImage();

    // load expected image
    String path = filePathBuilder.buildImagePath("addressresultmap", address, "png");
    BufferedImage expectedImage = imageReader.readWithDefault(path, actualImage);

    ImageDiffer imageDiffer = new ImageDiffer();
    ImageDiff imageDiff = imageDiffer.makeDiff(expectedImage, actualImage);

    assertThat(imageDiff.hasDiff()).isFalse();
  }

  @And("^User click on map button$")
  public void userClickOnMapButton() throws Throwable {
    Thread.sleep(5000);
    addressSearchPage.clickMapButton();
  }

  @And("^User click on label button$")
  public void userClickOnLabelButton() throws Throwable {
    Thread.sleep(5000);
    addressSearchPage.clickLabelButton();
  }
}
