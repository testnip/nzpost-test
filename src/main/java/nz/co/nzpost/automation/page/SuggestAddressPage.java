package nz.co.nzpost.automation.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import nz.co.nzpost.automation.annotations.Page;
import nz.co.nzpost.automation.domain.AddressSuggestion;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

@Page
public class SuggestAddressPage {

  private final String host;

  @Autowired
  public SuggestAddressPage(@Value("${host}") String host) {
    this.host = host;
  }

  public void open() {
    Selenide.open(host + "/tools/address-report/suggest");
  }

  public void submitForm(AddressSuggestion addressSuggestion) {
    // enter fullname
    SelenideElement suggestFullName = $(".autotest-suggest-full-name");
    suggestFullName.sendKeys(addressSuggestion.getFullName());

    // enter email
    SelenideElement suggestEmail = $(".autotest-suggest-email");
    suggestEmail.sendKeys(addressSuggestion.getEmail());

    // enter street
    SelenideElement suggestStreet = $(".autotest-suggest-street-address");
    suggestStreet.sendKeys(addressSuggestion.getStreet());

    // enter suburb
    SelenideElement suggestSuburb = $(".autotest-suggest-suburb");
    suggestSuburb.sendKeys(addressSuggestion.getSuburb());

    // enter city
    SelenideElement suggestCity = $(".autotest-suggest-city-or-town");
    suggestCity.sendKeys(addressSuggestion.getCity());

    // select my address radio button
    if (addressSuggestion.getMyAddress()) {
      $(".autotest-address-confirmed-true").click();
    } else {
      $(".autotest-address-confirmed-false").click();
    }

    // enter comments
    SelenideElement suggestComments = $(".autotest-suggest-comments");
    suggestComments.sendKeys(addressSuggestion.getComments());
  }

  public void clickSubmitButton() {
    $(".autotest-suggest-submit").click();
  }

  public String nameNotification() {
    return $(".fullName-error-message").getText();
  }

  public String emailNotification() {
    return $(".email-error-message").getText();
  }

  public String streetNotification() {
    return $(".streetAddress-error-message").getText();
  }

  public String suburbNotification() {
    return $(".suburb-error-message").getText();
  }

  public String cityNotification() {
    return $(".cityOrTown-error-message").getText();
  }

  public boolean disabledSubmitButton() {
    String value = $(".autotest-suggest-submit >button[type='submit']").attr("disabled");
    return Boolean.parseBoolean(value);
  }

  public int getRequiredFieldNotication() {
    List<WebElement> errorContainer = WebDriverRunner.getWebDriver().findElements(By.className("form-text-field-error-message"));
    int span = errorContainer.size();
    return span;
  }
}
