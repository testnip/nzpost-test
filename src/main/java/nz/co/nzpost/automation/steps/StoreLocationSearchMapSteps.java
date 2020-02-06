package nz.co.nzpost.automation.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.co.nzpost.automation.domain.Address;
import nz.co.nzpost.automation.domain.MapHolder;
import nz.co.nzpost.automation.io.FilePathBuilder;
import nz.co.nzpost.automation.io.ImageReader;
import nz.co.nzpost.automation.page.StoreLocatorPage;
import nz.co.nzpost.automation.screenshot.ScreenshotTaker;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import java.awt.image.BufferedImage;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class StoreLocationSearchMapSteps {

  private final MapHolder mapHolder;
  private final FilePathBuilder filePathBuilder;
  private final ImageReader imageReader;
  private final StoreLocatorPage storeLocatorPage;
  private final ScreenshotTaker screenshotTaker;

  @Autowired
  public StoreLocationSearchMapSteps(MapHolder mapHolder, FilePathBuilder filePathBuilder, ImageReader imageReader, StoreLocatorPage storeLocatorPage, ScreenshotTaker screenshotTaker) {
    this.mapHolder = mapHolder;
    this.filePathBuilder = filePathBuilder;
    this.imageReader = imageReader;
    this.storeLocatorPage = storeLocatorPage;
    this.screenshotTaker = screenshotTaker;
  }

  @Then("^User should see correct store locations on the map$")
  public void userShouldSeeCorrectStoreLocationsOnTheMap() throws Throwable {
    Thread.sleep(10000);

    // get search address
    Address address = mapHolder.get(Address.class);

    // capture actual image
    BufferedImage actualImage = storeLocatorPage.getMapImage();

    // load expected image
    String path = filePathBuilder.buildImagePath("locationresultmap", address.getValue(), "png");
    BufferedImage expectedImage = imageReader.readWithDefault(path, actualImage);

    ImageDiffer imageDiffer = new ImageDiffer();
    ImageDiff imageDiff = imageDiffer.makeDiff(expectedImage, actualImage);

    assertThat(imageDiff.hasDiff()).isFalse();
  }

  @When("^User select \"([^\"]*)\" with location id \"([^\"]*)\" displayed on the map$")
  public void userSelectWithLocationIdDisplayedOnTheMap(String location, String locationid) throws Throwable {
    storeLocatorPage.selectLocationIconMap(locationid);
  }

  @Then("^User should see \"([^\"]*)\" highlighted in the result list$")
  public void userShouldSeeHighlightedInTheResultList(String location) throws Throwable {
    assertThat(storeLocatorPage.getSelectedListValue()).isEqualToIgnoringCase(location);
  }
}
