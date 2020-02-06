package nz.co.nzpost.automation.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.co.nzpost.automation.domain.ContactDetails;
import nz.co.nzpost.automation.domain.MapHolder;
import nz.co.nzpost.automation.domain.PaymentDetails;
import nz.co.nzpost.automation.domain.RedeliveryDetails;
import nz.co.nzpost.automation.io.ModelFactory;
import nz.co.nzpost.automation.page.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class ParcelForYouPaymentSteps {

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
  private final ParcelForYouSummaryPage summaryPage;
  private final ParcelForYouPaymentPage paymentPage;
  private final ParcelForYouAuthorisedLeaveSteps authorisedLeaveSteps;
  private String contactDetailsType;

  @Autowired
  public ParcelForYouPaymentSteps(ParcelForYouPaymentPage paymentPage, ParcelForYouAuthorisedLeaveSteps authorisedLeaveSteps, ParcelForYouSummaryPage summaryPage, ParcelForYouAuthorisedLeavePage authorisedLeavePage, ParcelForYouDateTimeSteps dateTimeSteps, ParcelForYouContactDetailsSteps contactDetailsSteps, ParcelForYouDateTimePage dateTimePage, ContactDetails contactDetails, RedeliveryDetails redeliveryDetails, ParcelForYouPage parcelForYouPage, ParcelForYouOriginalDeliveryAddressPage originalDeliveryAddressPage, ParcelForYouRedeliveryOptionsPage redeliveryOptionsPage, ParcelForYouNewlDeliveryAddressPage newlDeliveryAddressPage, ParceForYouContactDetailsPage contactDetailsPage, ParcelForYouSuccessPage successPage, MapHolder mapHolder, ModelFactory modelFactory) {

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
    this.summaryPage = summaryPage;
    this.authorisedLeaveSteps = authorisedLeaveSteps;
    this.paymentPage = paymentPage;
  }


  @And("^User is on payment page$")
  public void userIsOnPaymentPage() throws Throwable {
    dateTimeSteps.userIsOnDateTimePage();
    dateTimePage.selectFromDateCard();
    dateTimePage.selectFromEveningCard();
    //dateTimePage.selectFromMorningCard();
    String date = dateTimePage.getDateText();
    mapHolder.put(RedeliveryDetails.class, redeliveryDetails);
    redeliveryDetails.setRedeliverDate(date);
    String time = dateTimePage.getTimeText();
    redeliveryDetails.setRedeliverTime(time);
    mapHolder.put(RedeliveryDetails.class, redeliveryDetails);
    dateTimePage.clickNext();
    authorisedLeavePage.selectFromLeaveMyParcelOptions("Leave by the front door");
    authorisedLeavePage.clickNext();
    summaryPage.clickCheckbox();
    summaryPage.clickSubmit();
  }

  @Given("^User has payment details \"([^\"]*)\"$")
  public void userHasPaymentDetails(String paymentDetailsType) throws Throwable {
    PaymentDetails paymentDetails = modelFactory.readFromJson(paymentDetailsType, PaymentDetails.class);
    mapHolder.put(PaymentDetails.class, paymentDetails);
  }

  @Then("^User should see postcode validation error$")
  public void userShouldSeePostcodeValidationError() throws Throwable {
    assertThat(paymentPage.postcodeNotification()).isEqualToIgnoringCase("Please enter a valid postcode, avoiding characters such as #$%.");
  }

  @Then("^User should see country validation error$")
  public void userShouldSeeCountryValidationError() throws Throwable {
    assertThat(paymentPage.countryNotification()).isEqualToIgnoringCase("Please enter a valid country, avoiding characters such as #$%.");
  }

  @When("^User enters details on payment form$")
  public void userEntersDetailsOnPaymentForm() throws Throwable {
    paymentPage.clearDefaultValues();
    Thread.sleep(5000);
    PaymentDetails paymentDetails = mapHolder.get(PaymentDetails.class);
    paymentPage.submitDetails(paymentDetails);
  }

  @When("^User submit card details$")
  public void userSubmitCardDetails() throws Throwable {
    PaymentDetails paymentDetails = mapHolder.get(PaymentDetails.class);
    paymentPage.submitDetails(paymentDetails);
    paymentPage.clickPay();
  }

  @When("^User submit details$")
  public void userSubmitDetails() throws Throwable {
    paymentPage.clearDefaultValues();
    paymentPage.clickPay();
    Thread.sleep(8000);
  }

  @When("^User submits payment form$")
  public void userSubmitsPaymentForm() throws Throwable {
    paymentPage.clearDefaultValues();
    PaymentDetails paymentDetails = mapHolder.get(PaymentDetails.class);
    paymentPage.submitDetails(paymentDetails);
    paymentPage.clickPay();
  }

  @Then("^User should see required errors$")
  public void userShouldSeeRequiredErrors() throws Throwable {
    assertThat(paymentPage.getRequiredFieldNotification()).isEqualTo(11);
  }

  @Then("^User should see credit card number validation error$")
  public void userShouldSeeCreditCardNumberValidationError() throws Throwable {
    assertThat(paymentPage.cardNumberNotification()).isEqualToIgnoringCase("Invalid card number. Please try again.");
  }

  @Then("^User should see credit card name error$")
  public void userShouldSeeCreditCardNameError() throws Throwable {
    assertThat(paymentPage.cardNameNotification()).isEqualToIgnoringCase("Cardholder name is required.");
  }

  @Then("^User should see credit card expiry date validation error$")
  public void userShouldSeeCreditCardExpiryDateValidationError() throws Throwable {
    assertThat(paymentPage.cardExpiryNotification()).isEqualToIgnoringCase("Invalid expiration date. Please try again.");
  }

  @Then("^User should see security code validation error$")
  public void userShouldSeeSecurityCodeValidationError() throws Throwable {
    assertThat(paymentPage.cardCvvNotification()).isEqualToIgnoringCase("Invalid CVV/CVC. Please try again.");
  }

  @Then("^User should see timeout error$")
  public void userShouldSeeTimeoutError() throws Throwable {
    Thread.sleep(2000);
    assertThat(paymentPage.cardErrorModalMessage()).isEqualToIgnoringCase("We are currently experiencing problems with our payment system. Please contact us to complete this purchase or try again later.");
  }

  @Then("^User should see declined error$")
  public void userShouldSeeDeclinedError() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    assertThat(paymentPage.cardErrorModalMessage()).isEqualToIgnoringCase("You may have entered your card details incorrectly, or have insufficient funds to complete this purchase. Please check and try again.");
  }

  @Then("^User should see invalid street validation error$")
  public void userShouldSeeInvalidStreetValidationError() throws Throwable {
    assertThat(paymentPage.streetNotification()).isEqualToIgnoringCase("Please enter a valid street, PO Box or Community Box, avoiding characters such as #$%.");
  }

  @Then("^User should see invalid city validation error$")
  public void userShouldSeeInvalidCityValidationError() throws Throwable {
    assertThat(paymentPage.cityNotification()).isEqualToIgnoringCase("Please enter a valid city or town, avoiding characters such as #$%.");
  }
}
