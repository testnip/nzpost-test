package nz.co.nzpost.automation.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import nz.co.nzpost.automation.annotations.Page;
import nz.co.nzpost.automation.domain.AddressSuggestion;

import static com.codeborne.selenide.Selenide.$;

@Page
public class SubmittedSuggestAddressPage {

  public AddressSuggestion getSubmittedSuggestAddress() {
    AddressSuggestion addressSuggestion = new AddressSuggestion();

    // get fullname
    SelenideElement submittedFullName = $(".submitted-fullname");
    addressSuggestion.setFullName(submittedFullName.getText());

    // get email element
    SelenideElement submittedEmail = $(".submitted-email");
    addressSuggestion.setEmail(submittedEmail.getText());

    // get street element
    SelenideElement submittedStreet = $(".submitted-street");
    addressSuggestion.setStreet(submittedStreet.getText());

    // get suburb element
    SelenideElement submittedSuburb = $(".submitted-suburb");
    addressSuggestion.setSuburb(submittedSuburb.getText());

    // get city
    SelenideElement submittedCity = $(".submitted-city");
    addressSuggestion.setCity(submittedCity.getText());

    // get radio button
    SelenideElement submittedRadioButton = $(".address-confirmed");
    addressSuggestion.setMyAddress(submittedRadioButton.getText().equalsIgnoreCase("This is my address."));

    // get comments
    SelenideElement submittedComments = $(".submitted-comments");
    addressSuggestion.setComments(submittedComments.getText());

    return addressSuggestion;
  }

  public boolean hasBackToAddressPostcodeFinderButton() {
    return $(".back-to-apf>a").waitUntil(Condition.appear, 2000).exists();
  }

  public void clickBackToAddressPostCodeFinderButton() {
    $(".back-to-apf>a").click();
  }
}
