package nz.co.nzpost.automation.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.co.nzpost.automation.domain.ContactDetails;
import nz.co.nzpost.automation.domain.MapHolder;
import nz.co.nzpost.automation.domain.RedeliveryDetails;
import nz.co.nzpost.automation.io.ModelFactory;
import nz.co.nzpost.automation.page.*;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

public class ParcelForYouAuthorisedLeaveSteps {

  private final ParcelForYouPage parcelForYouPage;
  private final ParcelForYouOriginalDeliveryAddressPage originalDeliveryAddressPage;
  private final ParcelForYouRedeliveryOptionsPage redeliveryOptionsPage;
  private final ParcelForYouNewlDeliveryAddressPage newlDeliveryAddressPage;
  private final ParceForYouContactDetailsPage contactDetailsPage;
  private final ParcelForYouSuccessPage successPage;
  private final MapHolder mapHolder;
  private final ModelFactory modelFactory;
  private final RedeliveryDetails redeliveryDetails;
  private final ParcelForYouDateTimePage dateTimePage;
  private final ContactDetails contactDetails;
  private final ParcelForYouContactDetailsSteps contactDetailsSteps;
  private final ParcelForYouDateTimeSteps dateTimeSteps;
  private final ParcelForYouAuthorisedLeavePage authorisedLeavePage;
  private String contactDetailsType;

  @Autowired
  public ParcelForYouAuthorisedLeaveSteps(ParcelForYouAuthorisedLeavePage authorisedLeavePage, ParcelForYouDateTimeSteps dateTimeSteps, ParcelForYouContactDetailsSteps contactDetailsSteps, ParcelForYouDateTimePage dateTimePage, ContactDetails contactDetails, RedeliveryDetails redeliveryDetails, ParcelForYouPage parcelForYouPage, ParcelForYouOriginalDeliveryAddressPage originalDeliveryAddressPage, ParcelForYouRedeliveryOptionsPage redeliveryOptionsPage, ParcelForYouNewlDeliveryAddressPage newlDeliveryAddressPage, ParceForYouContactDetailsPage contactDetailsPage, ParcelForYouSuccessPage successPage, MapHolder mapHolder, ModelFactory modelFactory) {

    this.parcelForYouPage = parcelForYouPage;
    this.mapHolder = mapHolder;
    this.modelFactory = modelFactory;
    this.originalDeliveryAddressPage = originalDeliveryAddressPage;
    this.redeliveryOptionsPage = redeliveryOptionsPage;
    this.newlDeliveryAddressPage = newlDeliveryAddressPage;
    this.contactDetailsPage = contactDetailsPage;
    this.successPage = successPage;
    this.redeliveryDetails = redeliveryDetails;
    this.dateTimePage = dateTimePage;
    this.dateTimeSteps = dateTimeSteps;
    this.contactDetails = contactDetails;
    this.contactDetailsSteps = contactDetailsSteps;
    this.authorisedLeavePage = authorisedLeavePage;
  }

  @Given("^User is on authorised to leave page$")
  public void userIsOnAuthorisedToLeavePage() throws Throwable {
    dateTimeSteps.userIsOnDateTimePage();
    dateTimePage.selectFromDateCard();
    //ndateTimePage.selectFromMorningCard();
    String date = dateTimePage.getDateText();
    mapHolder.put(RedeliveryDetails.class, redeliveryDetails);
    redeliveryDetails.setRedeliverDate(date);
    String time = dateTimePage.getTimeText();
    redeliveryDetails.setRedeliverTime(time);
    mapHolder.put(RedeliveryDetails.class, redeliveryDetails);
    dateTimePage.clickNext();
  }

  @And("^User select leave my parcel option \"([^\"]*)\"$")
  public void userSelectLeaveMyParcelOption(String leaveOptions) throws Throwable {
    authorisedLeavePage.selectFromLeaveMyParcelOptions(leaveOptions);
  }

  @When("^User submit leave my parcel$")
  public void userSubmitLeaveMyParcel() throws Throwable {
    authorisedLeavePage.clickNext();
  }

  @And("^user enters instructions \"([^\"]*)\"$")
  public void userEntersInstructions(String otherInstructions) throws Throwable {
    authorisedLeavePage.enterInstructions(otherInstructions);
  }

  @Then("^User should see summary page$")
  public void userShouldSeeSummaryPage() throws Throwable {
    Thread.sleep(1000);
    assertThat(authorisedLeavePage.getSummaryHeading()).isEqualToIgnoringCase("Review & confirm");
  }
}
