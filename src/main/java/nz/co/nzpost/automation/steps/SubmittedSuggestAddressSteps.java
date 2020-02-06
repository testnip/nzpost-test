package nz.co.nzpost.automation.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.co.nzpost.automation.page.AddressSearchPage;
import nz.co.nzpost.automation.page.SubmittedSuggestAddressPage;
import org.springframework.beans.factory.annotation.Autowired;

public class SubmittedSuggestAddressSteps {
  private final SubmittedSuggestAddressPage submittedSuggestAddressPage;
  private final AddressSearchPage addressSearchPage;

  @Autowired
  public SubmittedSuggestAddressSteps(SubmittedSuggestAddressPage submittedSuggestAddressPage, AddressSearchPage addressSearchPage) {
    this.submittedSuggestAddressPage = submittedSuggestAddressPage;
    this.addressSearchPage = addressSearchPage;
  }

  @When("^User select back to address postcode finder button$")
  public void userSelectBackToAddressPostcodeFinderButton() throws Throwable {
    submittedSuggestAddressPage.clickBackToAddressPostCodeFinderButton();
  }

  @Then("^User should see address postcode finder page$")
  public void userShouldSeeAddressPostcodeFinderPage() throws Throwable {
    addressSearchPage.open();
  }
}
