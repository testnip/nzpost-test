package nz.co.nzpost.automation.steps;


import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.co.nzpost.automation.page.AddressSearchPage;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;


public class NavigateToSuggestAnAddressSteps {

  private final AddressSearchPage addressSearchPage;

  @Autowired
  public NavigateToSuggestAnAddressSteps(AddressSearchPage addressSearchPage) {
    this.addressSearchPage = addressSearchPage;
  }

  @When("^User click on suggest an address button$")
  public void userClickOnSuggestAnAddressButton() throws Throwable {
    addressSearchPage.clickSuggestAnAddressButton();
  }

  @Then("^User should see suggest an address page$")
  public void userShouldSeeSuggestAnAddressPage() throws Throwable {
    assertThat(addressSearchPage.getSuggestAnAddressHeading()).isEqualToIgnoringCase("Suggest an address");
  }
}
