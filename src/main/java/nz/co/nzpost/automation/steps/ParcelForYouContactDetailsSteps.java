package nz.co.nzpost.automation.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.co.nzpost.automation.domain.*;
import nz.co.nzpost.automation.io.ModelFactory;
import nz.co.nzpost.automation.page.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class ParcelForYouContactDetailsSteps {

  private final ParcelForYouPage parcelForYouPage;
  private final ParcelForYouOriginalDeliveryAddressPage originalDeliveryAddressPage;
  private final ParcelForYouRedeliveryOptionsPage redeliveryOptionsPage;
  private final ParcelForYouNewlDeliveryAddressPage newlDeliveryAddressPage;
  private final ParceForYouContactDetailsPage contactDetailsPage;
  private final ParcelForYouSuccessPage successPage;
  private final MapHolder mapHolder;
  private final ModelFactory modelFactory;
  private final RedeliveryDetails redeliveryDetails;

  @Autowired
  public ParcelForYouContactDetailsSteps(RedeliveryDetails redeliveryDetails, ParcelForYouPage parcelForYouPage, ParcelForYouOriginalDeliveryAddressPage originalDeliveryAddressPage, ParcelForYouRedeliveryOptionsPage redeliveryOptionsPage, ParcelForYouNewlDeliveryAddressPage newlDeliveryAddressPage, ParceForYouContactDetailsPage contactDetailsPage, ParcelForYouSuccessPage successPage, MapHolder mapHolder, ModelFactory modelFactory) {
    this.parcelForYouPage = parcelForYouPage;
    this.mapHolder = mapHolder;
    this.modelFactory = modelFactory;
    this.originalDeliveryAddressPage = originalDeliveryAddressPage;
    this.redeliveryOptionsPage = redeliveryOptionsPage;
    this.newlDeliveryAddressPage = newlDeliveryAddressPage;
    this.contactDetailsPage = contactDetailsPage;
    this.successPage = successPage;
    this.redeliveryDetails = redeliveryDetails;
  }

  @Given("^User is on contact details page$")
  public void userIsOnContactDetailsPage() throws Throwable {
    mapHolder.put(RedeliveryDetails.class, redeliveryDetails);
    parcelForYouPage.gotoOriginalDeliveryAddressPage(mapHolder);
    originalDeliveryAddressPage.gotoRedeliveryOptionPage(mapHolder);
    redeliveryOptionsPage.gotoContactDetailsPage();
    Thread.sleep(1000);
  }

  @Given("^User has contact details \"([^\"]*)\"$")
  public void userHasContactDetails(String contactDetailsType) throws Throwable {
   ContactDetails contactDetails = modelFactory.readFromJson(contactDetailsType, ContactDetails.class);
    mapHolder.put(ContactDetails.class, contactDetails);
  }

  @When("^User submits the contact details form$")
  public void userSubmitsTheContactDetailsForm() throws Throwable {
    ContactDetails contactDetails = mapHolder.get(ContactDetails.class);
    contactDetailsPage.submitdetails(contactDetails);
    contactDetailsPage.clickNext();
}

  @When("^User enters details on contact details form$")
  public void userEntersDetailsOnContactDetailsForm() throws Throwable {
    ContactDetails contactDetails = mapHolder.get(ContactDetails.class);
    contactDetailsPage.submitdetails(contactDetails);
    contactDetailsPage.clickNext();
  }

  @Then("^User should see first name validation error$")
  public void userShouldSeeFirstNameValidationError() throws Throwable {
    assertThat(contactDetailsPage.firstNameNotification()).isEqualToIgnoringCase("Please enter a valid name, avoiding characters such as #$%.");
  }

  @Then("^User should see last name validation error$")
  public void userShouldSeeLastNameValidationError() throws Throwable {
    assertThat(contactDetailsPage.lastNamelNotification()).isEqualToIgnoringCase("Please enter a valid name, avoiding characters such as #$%.");
  }

  @Then("^User should see email detail validation error$")
  public void userShouldSeeEmailDetailValidationError() throws Throwable {
    assertThat(contactDetailsPage.emailNotification()).isEqualToIgnoringCase("The email address you have entered is invalid. Please check and try again.");
  }

  @Then("^User should see phone number validation error$")
  public void userShouldSeePhoneNumberValidationError() throws Throwable {
    assertThat(contactDetailsPage.phoneNotification()).isEqualToIgnoringCase("The phone number you have entered is invalid. Please check and try again, using only numbers.");
  }

  @Then("^User should see required details errors$")
  public void userShouldSeeRequiredDetailsErrors() throws Throwable {
    assertThat(contactDetailsPage.getRequiredFieldNotication()).isEqualTo(4);
  }

  @Then("^User should see date and time page$")
  public void userShouldSeeDateAndTimePage() throws Throwable {
    assertThat(contactDetailsPage.getDateandTimeHeading()).isEqualToIgnoringCase("Choose a new delivery date and time");
  }
}


