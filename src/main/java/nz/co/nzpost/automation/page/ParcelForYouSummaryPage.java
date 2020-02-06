package nz.co.nzpost.automation.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import net.bytebuddy.implementation.bytecode.Throw;
import nz.co.nzpost.automation.annotations.Page;
import nz.co.nzpost.automation.domain.ContactDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

@Page
public class ParcelForYouSummaryPage {

  private final String host;

  @Autowired
  public ParcelForYouSummaryPage(@Value("${host}") String host) {
    this.host = host;
  }

  public String getSummaryTrackingNumber() {
    SelenideElement summaryTrackingNumber = $(".tracking-number.value");
    String getSummaryTrackingNumber = summaryTrackingNumber.getText().replaceAll("\n", "");
    return getSummaryTrackingNumber;
  }

  public String getSummaryTrackingNumberTitle() {
    SelenideElement summaryTrackingNumberTitle = $(".tracking-number.label");
    String getSummaryTrackingNumberTitle = summaryTrackingNumberTitle.getText().replaceAll("\n", " ");
    return getSummaryTrackingNumberTitle;
  }

  public String getSummaryAddress() {
    SelenideElement summaryAddress = $(".address.value");
    String getSummaryAddress = summaryAddress.getText().replaceAll("\n", "");
    return getSummaryAddress;
  }

  public String getSummaryAddressTitle() {
    SelenideElement summarySameAddressTitle = $(".address.label");
    String getSummaryAddressTitle = summarySameAddressTitle.getText();
    return getSummaryAddressTitle;
  }

  public String getSummaryContactDetails() {
    SelenideElement summaryContactDetails = $(".contact-details.value");
    String getSummaryContactDetails = summaryContactDetails.getText();
    return  getSummaryContactDetails;
  }

  public String getSummaryContactDetailsTitle() {
    SelenideElement summaryContactDetailsTitle = $(".contact-details.label");
    String getSummaryContactDetailsTitle = summaryContactDetailsTitle.getText();
    return  getSummaryContactDetailsTitle;
  }

  public String getSummaryDateTime() {
    SelenideElement summaryDateTime = $(".datime.value");
    String getSummaryDateTime = summaryDateTime.getText();
    return getSummaryDateTime;
  }

  public String getSummaryDateTimeTitle() {
    SelenideElement summaryDateTimeTitle = $(".datetime.label");
    String getSummaryDateTimeTitle = summaryDateTimeTitle.getText();
    return getSummaryDateTimeTitle;
  }

  public String getSummaryRedeliveryInstructions() {
    SelenideElement summaryRedeliveryInstructions = $(".instructions.value");
    String getSummaryRedeliveryInstructions = summaryRedeliveryInstructions.getText();
    return getSummaryRedeliveryInstructions;
  }

  public String getSummaryRedeliveryInstructionsTitle() {
    SelenideElement summaryRedeliveryInstructionsTitle = $(".instructions.label");
    String getSummaryRedeliveryInstructionsTitle = summaryRedeliveryInstructionsTitle.getText();
    return getSummaryRedeliveryInstructionsTitle;
  }

  public void clickEditSameAddress() {
    $(".test-edit-link-address").click();
  }

  public void clickEditDifferentAddress() {
    $(".test-edit-link-address").click();
  }

  public void clickEditContactDetails() {
    $(".test-edit-link-contact").click();
  }

  public void clickEditDateTime() {
    $(".test-edit-link-datetime").click();
  }

  public void clickEditRedeliveryInstructions() {
    JavascriptExecutor je = (JavascriptExecutor) WebDriverRunner.getWebDriver();
    WebElement editRedeliveryInstructions = $(".test-edit-link-atl");
    je.executeScript("arguments[0].scrollIntoView(true);",editRedeliveryInstructions);
    editRedeliveryInstructions.click();
  }

  public void clickSubmit() {
    $(".redelivery-summary-submit").click();
  }

  public void clickCheckbox() throws Throwable {
    JavascriptExecutor je = (JavascriptExecutor) WebDriverRunner.getWebDriver();
    WebElement selectCheckbox =  $(".nzpost-checkbox-custom");
    je.executeScript("arguments[0].scrollIntoView(true);",selectCheckbox);
    selectCheckbox.click();
  }

  public void clickTermsConditions() {
    WebElement redeliveryLink = WebDriverRunner.getWebDriver().findElement(By.cssSelector("a[href*='https://www.nzpost.co.nz/about-us/who-we-are/terms-conditions/online-card-to-call-service']"));
    redeliveryLink.sendKeys(Keys.PAGE_DOWN);
    redeliveryLink.click();
  }

  public void clickOneTimeTermsConditions() {
    WebElement parcelLink = WebDriverRunner.getWebDriver().findElement(By.cssSelector("a[href*='https://www.nzpost.co.nz/about-us/who-we-are/terms-conditions/one-time-authority-to-leave']"));
    parcelLink.sendKeys(Keys.PAGE_DOWN);
    parcelLink.click();
  }

  public void openOnlineCardToCall() {
    Selenide.open("https://www.nzpost.co.nz/about-us/who-we-are/terms-conditions/online-card-to-call-service");
  }

  public void openOneTimeAuthority() {
    Selenide.open("https://www.nzpost.co.nz/about-us/who-we-are/terms-conditions/one-time-authority-to-leave");
  }

  public int getRequiredSummaryCard() {
    List<WebElement> summaryCards = WebDriverRunner.getWebDriver().findElements(By.className("redelivery-summary-cell"));
    int span = summaryCards.size();
    return span;
  }
}
