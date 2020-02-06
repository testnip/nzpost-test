package nz.co.nzpost.automation.steps;

import com.codeborne.selenide.WebDriverRunner;
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
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.assertj.core.api.Assertions.assertThat;

public class ParcelForYouSummarySteps {

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
  private final ParcelForYouAuthorisedLeaveSteps authorisedLeaveSteps;
  private String contactDetailsType;


  @Autowired
  public ParcelForYouSummarySteps(ParcelForYouAuthorisedLeaveSteps authorisedLeaveSteps, ParcelForYouSummaryPage summaryPage, ParcelForYouAuthorisedLeavePage authorisedLeavePage, ParcelForYouDateTimeSteps dateTimeSteps, ParcelForYouContactDetailsSteps contactDetailsSteps, ParcelForYouDateTimePage dateTimePage, ContactDetails contactDetails, RedeliveryDetails redeliveryDetails, ParcelForYouPage parcelForYouPage, ParcelForYouOriginalDeliveryAddressPage originalDeliveryAddressPage, ParcelForYouRedeliveryOptionsPage redeliveryOptionsPage, ParcelForYouNewlDeliveryAddressPage newlDeliveryAddressPage, ParceForYouContactDetailsPage contactDetailsPage, ParcelForYouSuccessPage successPage, MapHolder mapHolder, ModelFactory modelFactory) {

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
  }

  @Given("^User is on summary page$")
  public void userIsOnSummaryPage() throws Throwable {
    authorisedLeaveSteps.userIsOnAuthorisedToLeavePage();
    authorisedLeavePage.selectFromLeaveMyParcelOptions("Leave by the front door");
    authorisedLeavePage.clickNext();

  }

  @When("^User view information$")
  public void userViewInformation() throws Throwable {
    String expectedTrackingNumber = "8963063455001801AKL004AS";
  }

  @Then("^User should be shown tracking number information$")
  public void userShouldBeShownTrackingNumberInformation() throws Throwable {
    String inputTrackingNumber = redeliveryDetails.getTrackingNumber();
    assertThat(summaryPage.getSummaryTrackingNumber()).isEqualToIgnoringCase(inputTrackingNumber);
    assertThat(summaryPage.getSummaryTrackingNumberTitle()).isEqualToIgnoringCase("Tracking number");
  }

  @Then("^User should be shown redeliver same address information$")
  public void userShouldBeShownRedeliverSameAddressInformation() throws Throwable {
    String inputSameAddress = redeliveryDetails.getRedeliverAddress();
    assertThat(summaryPage.getSummaryAddress()).isEqualToIgnoringCase(inputSameAddress);
    assertThat(summaryPage.getSummaryAddressTitle()).isEqualToIgnoringCase("Redeliver to the same address");
  }

  @Then("^User should be shown contact details information$")
  public void userShouldBeShownContactDetailsInformation() throws Throwable {
    String[] getContactDetails = summaryPage.getSummaryContactDetails().split("\n");
    String getSummaryContactName = getContactDetails[0];
    String getSummaryContactComapny = getContactDetails[1];
    String getSummaryContactEmail = getContactDetails[2];
    String getSummaryContactPhoneNumber = getContactDetails[3];
    assertThat(getSummaryContactName).isEqualToIgnoringCase("Nipun Mahajan");
    assertThat(getSummaryContactComapny).isEqualTo("ABC123");
    assertThat(getSummaryContactEmail).isEqualToIgnoringCase("nipun.mahajan@nzpost.co.nz");
    assertThat(getSummaryContactPhoneNumber).isEqualToIgnoringCase("0284094560");
    assertThat(summaryPage.getSummaryContactDetailsTitle()).isEqualToIgnoringCase("Contact details");
  }

  @Then("^User should be shown redeliver date time information$")
  public void userShouldBeShownRedeliverDateTimeInformation() throws Throwable {
    String[] getSummaryDateTime = summaryPage.getSummaryDateTime().split("\n");
    String getSummaryDate = getSummaryDateTime[0];
    String getSummaryTime = getSummaryDateTime[1];
    String inputRedeliverDate = redeliveryDetails.getRedeliverDate();
    String inputRedeliverTime = redeliveryDetails.getRedeliverTime();
    assertThat(getSummaryDate).isEqualToIgnoringCase(inputRedeliverDate);
    assertThat(getSummaryTime).isEqualToIgnoringCase(inputRedeliverTime);
    assertThat(summaryPage.getSummaryDateTimeTitle()).isEqualToIgnoringCase("Redelivery date & time");
  }

  @Then("^User should be shown redelivery instructions information$")
  public void userShouldBeShownRedeliveryInstructionsInformation() throws Throwable {
    assertThat(summaryPage.getSummaryRedeliveryInstructions()).isEqualToIgnoringCase("Leave by the front door");
    assertThat(summaryPage.getSummaryRedeliveryInstructionsTitle()).isEqualToIgnoringCase("Redelivery instructions");
  }

  @When("^User click on edit on original delivery address$")
  public void userClickOnEditOnOriginalDeliveryAddress() throws Throwable {
    summaryPage.clickEditSameAddress();
  }

  @When("^User click on edit on contact details$")
  public void userClickOnEditOnContactDetails() throws Throwable {
    summaryPage.clickEditContactDetails();
  }

  @When("^User click on edit on date time$")
  public void userClickOnEditOnDateTime() throws Throwable {
    summaryPage.clickEditDateTime();
  }

  @When("^User click on edit on redelivery instructions$")
  public void userClickOnEditOnRedeliveryInstructions() throws Throwable {
    summaryPage.clickEditRedeliveryInstructions();
  }

  @When("^User submit summary$")
  public void userSubmitSummary() throws Throwable {
    summaryPage.clickSubmit();
  }

  @When("^User click on redelivery terms and conditions$")
  public void userClickOnRedeliveryTermsAndConditions() throws Throwable {
    summaryPage.clickTermsConditions();
    Thread.sleep(2000);
    ArrayList<String>  tabs = new ArrayList(WebDriverRunner.getWebDriver().getWindowHandles());
    WebDriverRunner.getWebDriver().switchTo().window(tabs.get(1));
  }

  @Then("^User should be redirected to card to call link$")
  public void userShouldBeRedirectedToCardToCallLink() throws Throwable {
    //summaryPage.openOnlineCardToCall();
    String actualOnelineCardToCallLink = url();
    Assert.assertEquals("https://www.nzpost.co.nz/about-us/who-we-are/terms-conditions/online-card-to-call-service", actualOnelineCardToCallLink);
  }

  @And("^User check terms and conditions$")
  public void userCheckTermsAndConditions() throws Throwable {
    summaryPage.clickCheckbox();
  }

  @Given("^User is on summary page without atl$")
  public void userIsOnSummaryPageWithoutAtl() throws Throwable {
    dateTimeSteps.userIsOnDateTimePage();
    dateTimePage.selectFromDateCard();
    dateTimePage.selectFromMorningCard();
    String date = dateTimePage.getDateText();
    mapHolder.put(RedeliveryDetails.class, redeliveryDetails);
    redeliveryDetails.setRedeliverDate(date);
    String time = dateTimePage.getTimeText();
    redeliveryDetails.setRedeliverTime(time);
    mapHolder.put(RedeliveryDetails.class, redeliveryDetails);
    dateTimePage.clickNext();
  }

  @Then("^User should be shown different cards$")
  public void userShouldBeShownDifferentCards() throws Throwable {
    assertThat(summaryPage.getRequiredSummaryCard()).isEqualTo(4);
  }

  @Then("^User should see success page$")
  public void userShouldSeeSuccessPage() throws Throwable {
    String submittedTrackingNumber = successPage.getSubmittedTrackingNumber();
    String submittedOriginalAddress = successPage.getSubmittedAddressDetails();
    String submittedDate = successPage.getSubmittedDateDetails();
    String inputTrackingNumber = redeliveryDetails.getTrackingNumber();
    String inputOriginalAddress = redeliveryDetails.getRedeliverAddress();
    String inputRedeliverTime = redeliveryDetails.getRedeliverTime().toLowerCase().replaceAll(" 7am-5pm", "").replace("daytime", " (daytime)").replace("evening"," (evening)").replaceAll("6pm-8:30pm","");
    String inputRedeliverDate = redeliveryDetails.getRedeliverDate().concat(inputRedeliverTime);
    assertThat(submittedTrackingNumber).isEqualTo(inputTrackingNumber);
    assertThat(submittedOriginalAddress).isEqualToIgnoringCase(inputOriginalAddress);
    assertThat(submittedDate).isEqualToIgnoringWhitespace(inputRedeliverDate);
  }

  @When("^User click on parcel redelivery terms and conditions$")
  public void userClickOnParcelRedeliveryTermsAndConditions() throws Throwable {
    summaryPage.clickOneTimeTermsConditions();
    Thread.sleep(2000);
    ArrayList<String>  tabs = new ArrayList(WebDriverRunner.getWebDriver().getWindowHandles());
    WebDriverRunner.getWebDriver().switchTo().window(tabs.get(1));
  }

  @Then("^User should be redirected to parcel card link$")
  public void userShouldBeRedirectedToParcelCardLink() throws Throwable {
    //summaryPage.openOneTimeAuthority();
    String actualOneTimeAuthorityLink = WebDriverRunner.url();
      Assert.assertEquals("https://www.nzpost.co.nz/about-us/who-we-are/terms-conditions/one-time-authority-to-leave", actualOneTimeAuthorityLink);
  }

  @Then("^User should be shown redeliver to the same address information$")
  public void userShouldBeShownRedeliverToTheSameAddressInformation() throws Throwable {
    assertThat(summaryPage.getSummaryTrackingNumberTitle()).isEqualToIgnoringCase("Tracking number");
    assertThat(summaryPage.getSummaryAddressTitle()).isEqualToIgnoringCase("Redelivery to the same address");
    assertThat(summaryPage.getSummaryContactDetailsTitle()).isEqualToIgnoringCase("Contact details");
    assertThat(summaryPage.getSummaryDateTimeTitle()).isEqualToIgnoringCase("Redelivery date & time");
  }

  @And("^variable set$")
  public void variableSet() throws Throwable {
    JavascriptExecutor je = (JavascriptExecutor) WebDriverRunner.getWebDriver();
   je.executeScript("window.__P4U_AUTOMATION_TEST__=true;");
    String testVal = je.executeScript("return window.__P4U_AUTOMATION_TEST__;").toString();
  }
}
