package nz.co.nzpost.automation.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import nz.co.nzpost.automation.annotations.Page;
import nz.co.nzpost.automation.browser.Browser;
import nz.co.nzpost.automation.domain.Address;
import nz.co.nzpost.automation.domain.ContactDetails;
import nz.co.nzpost.automation.domain.MapHolder;
import nz.co.nzpost.automation.domain.RedeliveryDetails;
import nz.co.nzpost.automation.io.ModelFactory;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.confirm;

@Page
public class ParceForYouContactDetailsPage {

  private final String host;

  @Autowired
  public ParceForYouContactDetailsPage(@Value("${host}") String host) {
    this.host = host;
  }

  public void submitdetails(ContactDetails contactDetails) {
    // enter firstname
    SelenideElement contactFirstlName = $(".contact-details-first-name-input");
    contactFirstlName.sendKeys(contactDetails.getFirstName());

    // enter lastname
    SelenideElement contactLastName = $(".contact-details-last-name-input");
    contactLastName.sendKeys(contactDetails.getLastName());

    // enter company
    SelenideElement contactCompanyName = $(".contact-details-company-input");
    contactCompanyName.sendKeys(contactDetails.getCompany());

    // enter email
    SelenideElement contactEmail = $(".contact-details-email-input");
    contactEmail.sendKeys(contactDetails.getEmail());

    // enter phone
    SelenideElement contactPhoneNumber = $(".contact-details-phone-input");
    contactPhoneNumber.sendKeys(contactDetails.getPhoneNumber());
  }

  public void clickNext() {
    $(".next-button.contact-details-submit").click();
  }

  public String firstNameNotification() {
    return $(".firstName-error-message").getText();
  }

  public String lastNamelNotification() {
    return $(".lastName-error-message").getText();
  }

  public String emailNotification() {
    return $(".email-error-message").getText();
  }

  public String phoneNotification() {
    return $(".phone-error-message").getText();
  }

  public String getDateandTimeHeading() {
    return $(".title-color-red-nz-post").getText();
  }

  public int getRequiredFieldNotication() {
    List<WebElement> errorContainer = WebDriverRunner.getWebDriver().findElements(By.className("form-text-field-error-message"));
    int span = errorContainer.size();
    return span;
  }
}
