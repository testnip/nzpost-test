package nz.co.nzpost.automation.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.co.nzpost.automation.domain.MapHolder;
import nz.co.nzpost.automation.domain.RedeliveryDetails;
import nz.co.nzpost.automation.io.ModelFactory;
import nz.co.nzpost.automation.page.ParcelForYouOriginalDeliveryAddressPage;
import nz.co.nzpost.automation.page.ParcelForYouPage;
import nz.co.nzpost.automation.page.ParcelForYouRedeliveryOptionsPage;
import org.springframework.beans.factory.annotation.Autowired;

import static com.codeborne.selenide.Selenide.title;
import static org.assertj.core.api.Assertions.assertThat;

public class ParcelForYouRedeliveryOptionsSteps {

  private final ParcelForYouPage parcelForYouPage;
  private final ParcelForYouOriginalDeliveryAddressPage originalDeliveryAddressPage;
  private final ParcelForYouRedeliveryOptionsPage redeliveryOptionsPage;
  private final MapHolder mapHolder;
  private final ModelFactory modelFactory;
  private final RedeliveryDetails redeliveryDetails;

  @Autowired
  public ParcelForYouRedeliveryOptionsSteps(RedeliveryDetails redeliveryDetails, ParcelForYouPage parcelForYouPage, ParcelForYouOriginalDeliveryAddressPage originalDeliveryAddressPage, MapHolder mapHolder, ModelFactory modelFactory, ParcelForYouRedeliveryOptionsPage redeliveryOptionsPage) {
    this.parcelForYouPage = parcelForYouPage;
    this.mapHolder = mapHolder;
    this.modelFactory = modelFactory;
    this.originalDeliveryAddressPage = originalDeliveryAddressPage;
    this.redeliveryOptionsPage = redeliveryOptionsPage;
    this.redeliveryDetails = redeliveryDetails;
  }

  @Given("^User is on redelivery options page$")
  public void userIsOnRedeliveryOptionsPage() throws Throwable {
    mapHolder.put(RedeliveryDetails.class, redeliveryDetails);
    parcelForYouPage.gotoOriginalDeliveryAddressPage(mapHolder);
    originalDeliveryAddressPage.gotoRedeliveryOptionPage(mapHolder);
  }

  @And("^User has selected redeliver to same address$")
  public void userHasSelectedRedeliverToSameAddress() throws Throwable {
    redeliveryOptionsPage.selectRedeliverSameAddress();
  }

  @When("^User submit option$")
  public void userSubmitOption() throws Throwable {
    redeliveryOptionsPage.clickNext();
  }

  @Then("^User should be shown date and time for redelivery$")
  public void userShouldBeShownDateAndTimeForRedelivery() throws Throwable {
    Thread.sleep(2000);
    assertThat(title()).isEqualToIgnoringCase("Parcel Redelivery Date Time");
  }

  @And("^User has selected redeliver to different address$")
  public void userHasSelectedRedeliverToDifferentAddress() throws Throwable {
    redeliveryOptionsPage.selectRedeliverDifferentAddress();
  }

  @Then("^User should be shown new delivery address option$")
  public void userShouldBeShownNewDeliveryAddressOption() throws Throwable {
    Thread.sleep(1000);
    assertThat(redeliveryOptionsPage.getNewDeliveryAddressTitle()).isEqualToIgnoringCase("Redeliver to a different address");
  }

  @When("^User submit previous$")
  public void userSubmitPrevious() throws Throwable {
    redeliveryOptionsPage.clickPrevious();
  }

  @Then("^User should be shown original delivery address$")
  public void userShouldBeShownOriginalDeliveryAddress() throws Throwable {
    assertThat(parcelForYouPage.getParcelDetailsHeading()).isEqualToIgnoringCase("Original delivery address");
  }

  @Then("^User should be shown contact details page$")
  public void userShouldBeShownContactDetailsPage() throws Throwable {
    Thread.sleep(1500);
    assertThat(redeliveryOptionsPage.getContactDetailsHeading()).isEqualToIgnoringCase("Contact details");
  }

  @And("^User has selected redeliver to collection point$")
  public void userHasSelectedRedeliverToCollectionPoint() throws Throwable {
    redeliveryOptionsPage.selectRedeliverCollectionPoint();
  }

  @Then("^User should be shown parcel collect redelivery$")
  public void userShouldBeShownParcelCollectRedelivery() throws Throwable {
    redeliveryOptionsPage.getParcelCollectHeading();
  }
}

