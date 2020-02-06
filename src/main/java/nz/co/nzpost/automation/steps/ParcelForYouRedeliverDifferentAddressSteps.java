package nz.co.nzpost.automation.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.co.nzpost.automation.domain.MapHolder;
import nz.co.nzpost.automation.domain.RedeliveryDetails;
import nz.co.nzpost.automation.io.ModelFactory;
import nz.co.nzpost.automation.page.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class ParcelForYouRedeliverDifferentAddressSteps {

  private final ParcelForYouPage parcelForYouPage;
  private final ParcelForYouOriginalDeliveryAddressPage originalDeliveryAddressPage;
  private final ParcelForYouRedeliveryOptionsPage redeliveryOptionsPage;
  private final ParcelForYouNewlDeliveryAddressPage newlDeliveryAddressPage;
  private final ParceForYouContactDetailsPage contactDetailsPage;
  private final ParcelForYouSuccessPage successPage;
  private final MapHolder mapHolder;
  private final ModelFactory modelFactory;
  private final RedeliveryDetails redeliveryDetails;
  private final ParcelForYouContactDetailsSteps contactDetailsSteps;
  private final ParcelForYouDateTimePage dateTimePage;
  private final ParcelForYouAuthorisedLeavePage authorisedLeavePage;
  private final ParcelForYouSummaryPage summaryPage;
  private String contactDetailsType;

  @Autowired
  public ParcelForYouRedeliverDifferentAddressSteps(ParcelForYouSummaryPage summaryPage, ParcelForYouAuthorisedLeavePage authorisedLeavePage, ParcelForYouDateTimePage dateTimePage, ParcelForYouContactDetailsSteps contactDetailsSteps, RedeliveryDetails redeliveryDetails, ParcelForYouPage parcelForYouPage, ParcelForYouOriginalDeliveryAddressPage originalDeliveryAddressPage, ParcelForYouRedeliveryOptionsPage redeliveryOptionsPage, ParcelForYouNewlDeliveryAddressPage newlDeliveryAddressPage, ParceForYouContactDetailsPage contactDetailsPage, ParcelForYouSuccessPage successPage, MapHolder mapHolder, ModelFactory modelFactory) {
    this.parcelForYouPage = parcelForYouPage;
    this.mapHolder = mapHolder;
    this.modelFactory = modelFactory;
    this.originalDeliveryAddressPage = originalDeliveryAddressPage;
    this.redeliveryOptionsPage = redeliveryOptionsPage;
    this.newlDeliveryAddressPage = newlDeliveryAddressPage;
    this.contactDetailsPage = contactDetailsPage;
    this.successPage = successPage;
    this.redeliveryDetails = redeliveryDetails;
    this.contactDetailsSteps = contactDetailsSteps;
    this.dateTimePage = dateTimePage;
    this.authorisedLeavePage = authorisedLeavePage;
    this.summaryPage = summaryPage;
  }

  @Given("^User is on contact page$")
  public void userIsOnContactPage() throws Throwable {
    mapHolder.put(RedeliveryDetails.class, redeliveryDetails);
    parcelForYouPage.gotoOriginalDeliveryAddressPage(mapHolder);
    originalDeliveryAddressPage.gotoRedeliveryOptionPage(mapHolder);
    redeliveryOptionsPage.gotoNewDeliveryAddressPage();
    newlDeliveryAddressPage.gotoContactPage(mapHolder);
  }

  @Then("^User should see success page for redelivery$")
  public void userShouldSeeSuccessPageForRedelivery() throws Throwable {
    String submittedTrackingNumber = successPage.getSubmittedTrackingNumber();
    String submittedNewAddress = successPage.getSubmittedAddressDetails();
    String submittedDate = successPage.getSubmittedDateDetails();
    assertThat(submittedTrackingNumber).isEqualTo("8963063455001801AKL004AS");
    assertThat(submittedNewAddress).isEqualTo("122 Kerwyn Avenue, East Tamaki, Auckland 2013");
    assertThat(submittedDate).isEqualTo("Thursday 9th MAY");
  }

  @Given("^User is on date time picker page$")
  public void userIsOnDateTimePickerPage() throws Throwable {
    this.userIsOnContactPage();
    contactDetailsType = "valid-company";
    contactDetailsSteps.userHasContactDetails(contactDetailsType);
    contactDetailsSteps.userSubmitsTheContactDetailsForm();
  }

  @Given("^User is on authorised page$")
  public void userIsOnAuthorisedPage() throws Throwable {
   this.userIsOnDateTimePickerPage();
    dateTimePage.selectFromDateCard();
    //dateTimePage.selectFromMorningCard();
    String date = dateTimePage.getDateText();
    mapHolder.put(RedeliveryDetails.class, redeliveryDetails);
    redeliveryDetails.setRedeliverDate(date);
    String time = dateTimePage.getTimeText();
    redeliveryDetails.setRedeliverTime(time);
    mapHolder.put(RedeliveryDetails.class, redeliveryDetails);
    dateTimePage.clickNext();
  }

  @Given("^User is on summary review page$")
  public void userIsOnSummaryReviewPage() throws Throwable {
    this.userIsOnAuthorisedPage();
    authorisedLeavePage.selectFromLeaveMyParcelOptions("Leave by the front door");
    authorisedLeavePage.clickNext();
  }

  @Then("^User should be shown redeliver different address information$")
  public void userShouldBeShownRedeliverDifferentAddressInformation() throws Throwable {
    String inputDifferentAddress = redeliveryDetails.getRedeliverDifferentAddress();
    assertThat(summaryPage.getSummaryAddress()).isEqualToIgnoringCase(inputDifferentAddress);
    assertThat(summaryPage.getSummaryAddressTitle()).isEqualToIgnoringCase("Redeliver to a different address");
  }

  @And("^User should be shown different summary cards$")
  public void userShouldBeShownDifferentSummaryCards() throws Throwable {
    assertThat(summaryPage.getRequiredSummaryCard()).isEqualTo(5);
  }

  @When("^User click on edit on different address$")
  public void userClickOnEditOnDifferentAddress() throws Throwable {
    summaryPage.clickEditDifferentAddress();
  }

  @Given("^User is on summary review page without atl$")
  public void userIsOnSummaryReviewPageWithoutAtl() throws Throwable {
    this.userIsOnDateTimePickerPage();
    dateTimePage.selectFromDateCard();
    //dateTimePage.selectFromMorningCard();
    String date = dateTimePage.getDateText();
    mapHolder.put(RedeliveryDetails.class, redeliveryDetails);
    redeliveryDetails.setRedeliverDate(date);
    String time = dateTimePage.getTimeText();
    redeliveryDetails.setRedeliverTime(time);
    mapHolder.put(RedeliveryDetails.class, redeliveryDetails);
    dateTimePage.clickNext();
  }

  @Then("^User should see success page for different address$")
  public void userShouldSeeSuccessPageForDifferentAddress() throws Throwable {
    String submittedTrackingNumber = successPage.getSubmittedTrackingNumber();
    String submittedOriginalAddress = successPage.getSubmittedAddressDetails();
    String submittedDate = successPage.getSubmittedDateDetails();
    String inputTrackingNumber = redeliveryDetails.getTrackingNumber();
    String inputDifferentAddress = redeliveryDetails.getRedeliverDifferentAddress();
    String inputRedeliverTime = redeliveryDetails.getRedeliverTime().toLowerCase().replaceAll(" 7am-5pm", "").replace("daytime", " (daytime)");
    String inputRedeliverDate = redeliveryDetails.getRedeliverDate().concat(inputRedeliverTime);
    assertThat(submittedTrackingNumber).isEqualTo(inputTrackingNumber);
    assertThat(submittedOriginalAddress).isEqualToIgnoringCase(inputDifferentAddress);
    assertThat(submittedDate).isEqualToIgnoringCase(inputRedeliverDate);
  }
}


