package nz.co.nzpost.automation.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.co.nzpost.automation.domain.MapHolder;
import nz.co.nzpost.automation.domain.RedeliveryDetails;
import nz.co.nzpost.automation.io.ModelFactory;
import nz.co.nzpost.automation.page.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class ParcelForYouCollectionPointSteps {

  private final ParcelForYouPage parcelForYouPage;
  private final ParcelForYouOriginalDeliveryAddressPage originalDeliveryAddressPage;
  private final ParcelForYouRedeliveryOptionsPage redeliveryOptionsPage;
  private final MapHolder mapHolder;
  private final ModelFactory modelFactory;
  private final RedeliveryDetails redeliveryDetails;
  private final ParcelForYouCollectionPointPage collectionPointPage;
  private final ParcelForYouContactDetailsSteps contactDetailsSteps;
  private final ParcelForYouSummaryPage summaryPage;
  private final ParcelForYouSuccessPage successPage;
  private String contactDetailsType;

  @Autowired
  public ParcelForYouCollectionPointSteps(ParcelForYouSuccessPage successPage, ParcelForYouSummaryPage summaryPage, ParcelForYouContactDetailsSteps contactDetailsSteps, ParcelForYouCollectionPointPage collectionPointPage, RedeliveryDetails redeliveryDetails, ParcelForYouPage parcelForYouPage, ParcelForYouOriginalDeliveryAddressPage originalDeliveryAddressPage, MapHolder mapHolder, ModelFactory modelFactory, ParcelForYouRedeliveryOptionsPage redeliveryOptionsPage) {
    this.parcelForYouPage = parcelForYouPage;
    this.mapHolder = mapHolder;
    this.modelFactory = modelFactory;
    this.originalDeliveryAddressPage = originalDeliveryAddressPage;
    this.redeliveryOptionsPage = redeliveryOptionsPage;
    this.redeliveryDetails = redeliveryDetails;
    this.collectionPointPage = collectionPointPage;
    this.summaryPage = summaryPage;
    this.contactDetailsSteps = contactDetailsSteps;
    this.successPage = successPage;
  }

  @And("^User is on parcel collection page$")
  public void userIsOnParcelCollectionPage() throws Throwable {
    mapHolder.put(RedeliveryDetails.class, redeliveryDetails);
    parcelForYouPage.gotoOriginalDeliveryAddressPage(mapHolder);
    originalDeliveryAddressPage.gotoRedeliveryOptionPage(mapHolder);
    redeliveryOptionsPage.gotoParcelCollectionPage();
  }

  @And("^User has selected parcel collection location$")
  public void userHasSelectedParcelCollectionLocation() throws Throwable {
    collectionPointPage.selectParcelCollectionLocation();
  }

  @When("^User submit location$")
  public void userSubmitLocation() throws Throwable {
    collectionPointPage.clickNext();
  }

  @And("^User is on summary review page with collection point address$")
  public void userIsOnSummaryReviewPageWithCollectionPointAddress() throws Throwable {
    mapHolder.put(RedeliveryDetails.class, redeliveryDetails);
    parcelForYouPage.gotoOriginalDeliveryAddressPage(mapHolder);
    originalDeliveryAddressPage.gotoRedeliveryOptionPage(mapHolder);
    redeliveryOptionsPage.gotoParcelCollectionPage();
    collectionPointPage.gotoContactDetailsPage();
    contactDetailsType = "valid-company";
    contactDetailsSteps.userHasContactDetails(contactDetailsType);
    contactDetailsSteps.userSubmitsTheContactDetailsForm();
  }

  @Then("^User should be shown redeliver collection point$")
  public void userShouldBeShownRedeliverCollectionPoint() throws Throwable {
    assertThat(summaryPage.getSummaryAddress()).isEqualToIgnoringCase("Manurewa NZ Post & Kiwibank181 Great South Road, Manurewa, Auckland 2102");
    assertThat(summaryPage.getSummaryAddressTitle()).isEqualToIgnoringCase("Redeliver to a collection point");
  }

  @And("^User should be shown different summary cards for parcel collection$")
  public void userShouldBeShownDifferentSummaryCardsForParcelCollection() throws Throwable {
    assertThat(summaryPage.getRequiredSummaryCard()).isEqualTo(3);
  }

  @Then("^User should see success page for parcel collection$")
  public void userShouldSeeSuccessPageForParcelCollection() throws Throwable {
    String submittedTrackingNumber = successPage.getSubmittedTrackingNumber();
    String submittedOriginalAddress = successPage.getSubmittedAddressDetails().replaceAll("\n", " ");
    //String submittedDate = successPage.getSubmittedDateDetails();
    String inputTrackingNumber = redeliveryDetails.getTrackingNumber();
    //String inputOriginalAddress = redeliveryDetails.getRedeliverAddress();
    //String inputRedeliverTime = redeliveryDetails.getRedeliverTime().toLowerCase().replaceAll(" 7am-5pm", "").replace("daytime", " (daytime)");
    //String inputRedeliverDate = redeliveryDetails.getRedeliverDate();
    assertThat(submittedTrackingNumber).isEqualTo(inputTrackingNumber);
    assertThat(submittedOriginalAddress).isEqualToIgnoringCase("Manurewa NZ Post & Kiwibank181 Great South Road, Manurewa, Auckland 2102");
    //assertThat(submittedDate).isEqualToIgnoringCase(inputRedeliverDate);
  }
}
