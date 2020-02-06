package nz.co.nzpost.automation.steps;

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

public class ParcelForYouDateTimeSteps {

  /*private final ParcelForYouPage parcelForYouPage;
  private final ParcelForYouOriginalDeliveryAddressPage originalDeliveryAddressPage;
  private final ParcelForYouRedeliveryOptionsPage redeliveryOptionsPage;
  private final ParcelForYouNewlDeliveryAddressPage newlDeliveryAddressPage;
  private final ParceForYouContactDetailsPage contactDetailsPage;
  private final ParcelForYouSuccessPage successPage;
  private final MapHolder mapHolder;
  private final ModelFactory modelFactory;
  private final RedeliveryDetails redeliveryDetails;*/
  private final ParcelForYouSuccessPage successPage;
  private final ParcelForYouDateTimePage dateTimePage;
  private final ContactDetails contactDetails;
  private final ParcelForYouContactDetailsSteps contactDetailsSteps;

  private String contactDetailsType;

  @Autowired
  public ParcelForYouDateTimeSteps(ParcelForYouContactDetailsSteps contactDetailsSteps, ContactDetails contactDetails, ParcelForYouDateTimePage dateTimePage, RedeliveryDetails redeliveryDetails, ParcelForYouPage parcelForYouPage, ParcelForYouOriginalDeliveryAddressPage originalDeliveryAddressPage, ParcelForYouRedeliveryOptionsPage redeliveryOptionsPage, ParcelForYouNewlDeliveryAddressPage newlDeliveryAddressPage, ParceForYouContactDetailsPage contactDetailsPage, ParcelForYouSuccessPage successPage, MapHolder mapHolder, ModelFactory modelFactory) {
    /*this.parcelForYouPage = parcelForYouPage;
    this.mapHolder = mapHolder;
    this.modelFactory = modelFactory;
    this.originalDeliveryAddressPage = originalDeliveryAddressPage;
    this.redeliveryOptionsPage = redeliveryOptionsPage;
    this.newlDeliveryAddressPage = newlDeliveryAddressPage;
    this.contactDetailsPage = contactDetailsPage;
    this.successPage = successPage;
    this.redeliveryDetails = redeliveryDetails;*/
    this.successPage = successPage;
    this.dateTimePage = dateTimePage;
    this.contactDetails = contactDetails;
    this.contactDetailsSteps = contactDetailsSteps;

  }

  @And("^User select date and time \"([^\"]*)\" \"([^\"]*)\"$")
  public void userSelectDateAndTime(String day, String time) throws Throwable {
    dateTimePage.selectFromDateCard();
    dateTimePage.selectFromMorningCard();
  }

  @Given("^User is on date time page$")
  public void userIsOnDateTimePage() throws Throwable {
   /* mapHolder.put(RedeliveryDetails.class, redeliveryDetails);
    parcelForYouPage.gotoOriginalDeliveryAddressPage(mapHolder);
    originalDeliveryAddressPage.gotoRedeliveryOptionPage(mapHolder);
    redeliveryOptionsPage.gotoContactDetailsPage();*/
    contactDetailsSteps.userIsOnContactDetailsPage();
    contactDetailsType = "valid-company";
    contactDetailsSteps.userHasContactDetails(contactDetailsType);
    contactDetailsSteps.userSubmitsTheContactDetailsForm();
  }

  @When("^User submit date and time$")
  public void userSubmitDateAndTime() throws Throwable {
    dateTimePage.clickNext();
  }

  @And("^User select date and time$")
  public void userSelectDateAndTime() throws Throwable {
    dateTimePage.selectFromDateCard();
    //dateTimePage.selectFromMorningCard();
  }

  @Then("^User should see redelivery instructions page$")
  public void userShouldSeeRedeliveryInstructionsPage() throws Throwable {
    assertThat(dateTimePage.getAuthorisedLeaveHeading()).isEqualToIgnoringCase("Authority to leave");
  }
}
