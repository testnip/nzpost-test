package nz.co.nzpost.automation.page;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import nz.co.nzpost.automation.annotations.Page;
import nz.co.nzpost.automation.domain.PaymentDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

@Page
public class ParcelForYouPaymentPage {


  public void submitDetails(PaymentDetails paymentDetails) {

    // enter credit cardholder name
    SelenideElement cardName = $(".autotest-card-cardholder-name .autotest-text-field-input");
    cardName.sendKeys(paymentDetails.getCardName());

    // enter credit card number
    SelenideElement cardNumber = $(".autotest-card-number-field-input .autotest-text-field-input");
    cardNumber.sendKeys(paymentDetails.getCardNumber());

    // enter credit card expiry date
    SelenideElement expiryDate = $(".autotest-card-expiry-field-input .autotest-text-field-input");
    expiryDate.sendKeys(paymentDetails.getExpiryDate());

    // enter credit card expiry date
    SelenideElement cvvNumber = $(".autotest-card-cvv-field-input .autotest-text-field-input");
    cvvNumber.sendKeys(paymentDetails.getCvvNumber());

    // enter billing firstname
    SelenideElement billingFirstlName = $(".billing-details-first-name-input");
    billingFirstlName.sendKeys(paymentDetails.getFirstName());

    // enter billing lastname
    SelenideElement billingLastName = $(".billing-details-last-name-input");
    billingLastName.sendKeys(paymentDetails.getLastName());

    // enter company
    SelenideElement billingCompanyName = $(".billing-details-company-input");
    billingCompanyName.sendKeys(paymentDetails.getCompanyName());

    // enter country
    SelenideElement billingCountry = $(".billing-details-country-input");
    billingCountry.sendKeys(paymentDetails.getCountry());

    // enter address
    SelenideElement billingAddress = $(".billing-details-street-input");
    billingAddress.sendKeys(paymentDetails.getAddress());

    // enter suburb
    SelenideElement billingSuburb = $(".billing-details-suburb-input");
    billingSuburb.sendKeys(paymentDetails.getSuburb());

    // enter city
    SelenideElement billingCity = $(".billing-details-city-input");
    billingCity.sendKeys(paymentDetails.getCity());

    // enter postcode
    SelenideElement billingPostcode = $(".billing-details-postcode-input");
    billingPostcode.sendKeys(paymentDetails.getPostcode());
  }

  public void clickPay() {
    $(".autotest-redelivery-payment-next-button").click();
  }

  public String postcodeNotification() {
    return $(".postcode-error-message").getText();
  }

  public String countryNotification() {
    return $(".country-error-message").getText();
  }

  public String cardNameNotification() {
    return $(".CardHolderName-error-message").getText();
  }

  public String cardNumberNotification() {
    return $(".CardNumber-error-message").getText();
  }

  public String cardExpiryNotification() {
    return $(".cardExpiry-error-message").getText();
  }

  public String cardCvvNotification() {
    return $(".Cvc2-error-message").getText();
  }

  public String cardErrorModalMessage() {
    return $(".autotest-modal-description").getText();
  }

  public String streetNotification() {
    return $(".street-error-message").getText();
  }

  public String cityNotification() {
    return $(".city-error-message").getText();
  }
  
  public int getRequiredFieldNotication() {
    List<WebElement> errorContainer = WebDriverRunner.getWebDriver().findElements(By.className("form-text-field-error-message"));
    int span = errorContainer.size();
    return span;
  }

  public void clearDefaultValues() {
    List<WebElement> defaultvalues = WebDriverRunner.getWebDriver().findElements(By.className("form-text-field-input"));
    for (int i = 0; i < defaultvalues.size(); i++) {
      defaultvalues.get(i).clear();
    }
  }

  public int getRequiredFieldNotification() {
    List<WebElement> errorContainer = WebDriverRunner.getWebDriver().findElements(By.className("form-text-field-error-message"));
    int span = errorContainer.size();
    return span;
  }
}
