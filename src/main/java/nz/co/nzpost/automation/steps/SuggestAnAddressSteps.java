package nz.co.nzpost.automation.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.co.nzpost.automation.domain.AddressSuggestion;
import nz.co.nzpost.automation.domain.MapHolder;
import nz.co.nzpost.automation.io.ModelFactory;
import nz.co.nzpost.automation.page.SubmittedSuggestAddressPage;
import nz.co.nzpost.automation.page.SuggestAddressPage;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class SuggestAnAddressSteps {

  private final ModelFactory modelFactory;
  private final MapHolder mapHolder;
  private final SuggestAddressPage suggestAddressPage;
  private final SubmittedSuggestAddressPage submittedSuggestAddressPage;

  @Autowired
  public SuggestAnAddressSteps(ModelFactory modelFactory, MapHolder mapHolder, SuggestAddressPage suggestAddressPage, SubmittedSuggestAddressPage submittedSuggestAddressPage) {
    this.modelFactory = modelFactory;
    this.mapHolder = mapHolder;
    this.suggestAddressPage = suggestAddressPage;
    this.submittedSuggestAddressPage = submittedSuggestAddressPage;
  }

  @Given("^User is on suggest an address page$")
  public void userIsOnSuggestAnAddressPage() throws Throwable {
    suggestAddressPage.open();
  }

  @Given("^User has address suggestion \"([^\"]*)\"$")
  public void userHasAddressSuggestion(String addressSuggestionType) throws Throwable {
    AddressSuggestion addressSuggestion = modelFactory.readFromJson(addressSuggestionType, AddressSuggestion.class);
    mapHolder.put(AddressSuggestion.class, addressSuggestion);
  }

  @When("^User submits the address suggest form$")
  public void userSubmitsTheAddressSuggestForm() throws Throwable {
    AddressSuggestion addressSuggestion = mapHolder.get(AddressSuggestion.class);
    suggestAddressPage.submitForm(addressSuggestion);
    suggestAddressPage.clickSubmitButton();
  }

  @Then("^User should see the success page for the suggested address$")
  public void userShouldSeeTheSuccessPageForTheSuggestedAddress() throws Throwable {
    Thread.sleep(5000);
    AddressSuggestion submittedSuggestAddress = submittedSuggestAddressPage.getSubmittedSuggestAddress();
    AddressSuggestion enteredSuggestAddress = mapHolder.get(AddressSuggestion.class);
    assertThat(submittedSuggestAddress).isEqualToComparingFieldByField(enteredSuggestAddress);
  }

  @And("^User should see back to address and postcode finder page button$")
  public void userShouldSeeBackToAddressAndPostcodeFinderPageButton() throws Throwable {
    assertThat(submittedSuggestAddressPage.hasBackToAddressPostcodeFinderButton()).isTrue();
  }

  @When("^User enters address on address suggest form$")
  public void userEntersAddressOnAddressSuggestForm() throws Throwable {
    AddressSuggestion addressSuggestion = mapHolder.get(AddressSuggestion.class);
    suggestAddressPage.submitForm(addressSuggestion);
  }

  @And("^User should see submit address button disabled$")
  public void userShouldSeeSubmitAddressButtonDisabled() throws Throwable {
    assertThat(suggestAddressPage.disabledSubmitButton()).isTrue();
  }

  @Then("^User should see full name validation error$")
  public void userShouldSeeFullNameValidationError() throws Throwable {
    assertThat(suggestAddressPage.nameNotification()).isEqualToIgnoringCase("Please enter a valid name, avoiding characters such as #$%.");
  }

  @Then("^User should see email validation error$")
  public void userShouldSeeEmailValidationError() throws Throwable {
    assertThat(suggestAddressPage.emailNotification()).isEqualToIgnoringCase("Please enter your email address in this format: name@example.com and avoid characters such as #$%");
  }

  @When("^User miss any required field$")
  public void userMissAnyRequiredField() throws Throwable {
    AddressSuggestion addressSuggestion = mapHolder.get(AddressSuggestion.class);
    suggestAddressPage.submitForm(addressSuggestion);
  }

  @Then("^User should see street validation error$")
  public void userShouldSeeStreetValidationError() throws Throwable {
    assertThat(suggestAddressPage.streetNotification()).isEqualToIgnoringCase("Please enter a valid street, PO Box or Community Box, avoiding characters such as #$%.");
  }

  @Then("^User should see suburb validation error$")
  public void userShouldSeeSuburbValidationError() throws Throwable {
    assertThat(suggestAddressPage.suburbNotification()).isEqualToIgnoringCase("Please enter a valid suburb, avoiding characters such as #$%.");
  }

  @Then("^User should see city validation error$")
  public void userShouldSeeCityValidationError() throws Throwable {
    assertThat(suggestAddressPage.cityNotification()).isEqualToIgnoringCase("Please enter a valid city or town, avoiding characters such as #$%.");
  }

  @Then("^User should see required field errors$")
  public void userShouldSeeRequiredFieldErrors() throws Throwable {
    assertThat(suggestAddressPage.getRequiredFieldNotication()).isEqualTo(5);
  }
}
