package nz.co.nzpost.automation.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.co.nzpost.automation.domain.MapHolder;
import nz.co.nzpost.automation.domain.TrackingNumber;
import nz.co.nzpost.automation.io.ModelFactory;
import nz.co.nzpost.automation.page.ParcelForYouPage;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class ParcelForYouSteps {

  private final ParcelForYouPage parcelForYouPage;
  private final MapHolder mapHolder;
  private final ModelFactory modelFactory;

  @Autowired
  public ParcelForYouSteps(ParcelForYouPage parcelForYouPage, MapHolder mapHolder, ModelFactory modelFactory) {
    this.parcelForYouPage = parcelForYouPage;
    this.mapHolder = mapHolder;
    this.modelFactory = modelFactory;
  }

  @Given("^User is on parcel for you page$")
  public void userIsOnParcelForYouPage() throws Throwable {
    parcelForYouPage.open();
  }

  @Given("^User has tracking/CTC number \"([^\"]*)\"$")
  public void userHasTrackingCTCNumber(String number) throws Throwable {
    TrackingNumber trackingNumber = new TrackingNumber(number);
    mapHolder.put(TrackingNumber.class, trackingNumber);
  }

  @When("^User submit tracking number$")
  public void userSubmitTrackingNumber() throws Throwable {
    TrackingNumber trackingNumber = mapHolder.get(TrackingNumber.class);
    parcelForYouPage.enter(trackingNumber);
    parcelForYouPage.pressEnter();
  }

  @Then("^User should see tracking number is eligible for redelivery$")
  public void userShouldSeeTrackingNumberIsEligibleForRedelivery() throws Throwable {
    assertThat(parcelForYouPage.getParcelDetailsHeading()).isEqualToIgnoringCase("Original delivery address");
  }

  @Then("^User should see message \"([^\"]*)\"$")
  public void userShouldSeeMessage(String message) throws Throwable {
    assertThat(parcelForYouPage.getInvalidTrackingNumberMessage()).isEqualToIgnoringCase(message);
  }

  @When("^User submit tracking number using button$")
  public void userSubmitTrackingNumberUsingButton() throws Throwable {
    TrackingNumber trackingNumber = mapHolder.get(TrackingNumber.class);
    parcelForYouPage.enter(trackingNumber);
    parcelForYouPage.clickNext();
  }

  @Then("^User should see a message \"([^\"]*)\" not available for redelivery$")
  public void userShouldSeeAMessageNotAvailableForRedelivery(String message) throws Throwable {
    assertThat(parcelForYouPage.getNotEligibleForRedeliveryMessage()).isEqualToIgnoringCase(message);
  }
}
