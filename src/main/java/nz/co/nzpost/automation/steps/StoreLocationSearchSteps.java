package nz.co.nzpost.automation.steps;

import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.co.nzpost.automation.domain.Address;
import nz.co.nzpost.automation.domain.MapHolder;
import nz.co.nzpost.automation.domain.StoreLocationResult;
import nz.co.nzpost.automation.domain.StoreLocationSuggestion;
import nz.co.nzpost.automation.io.ModelFactory;
import nz.co.nzpost.automation.page.StoreLocatorPage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StoreLocationSearchSteps {

  private final StoreLocatorPage storeLocatorPage;
  private final MapHolder mapHolder;
  private final ModelFactory modelFactory;

  @Autowired
  public StoreLocationSearchSteps(StoreLocatorPage storeLocatorPage, MapHolder mapHolder, ModelFactory modelFactory) {
    this.storeLocatorPage = storeLocatorPage;
    this.mapHolder = mapHolder;
    this.modelFactory = modelFactory;
  }

  @Given("^User is on store locator page$")
  public void userIsOnStoreLocatorPage() throws Throwable {
    storeLocatorPage.open();
  }

  @When("^User performs store search$")
  public void userPerformsStoreSearch() throws Throwable {
    Address address = mapHolder.get(Address.class);
    storeLocatorPage.enter(address);
    storeLocatorPage.pressEnter();
  }

  @When("^User enters store search$")
  public void userEntersStoreSearch() throws Throwable {
    Address address = mapHolder.get(Address.class);
    storeLocatorPage.enter(address);
  }

  @When("^User selects the clear search icon$")
  public void userSelectsTheClearSearchIcon() throws Throwable {
    storeLocatorPage.clearSearch();
  }

  @Then("^Entered address should be cleared$")
  public void enteredAddressShouldBeCleared() throws Throwable {
    assertThat(storeLocatorPage.getSearchValue()).isEmpty();
  }

  @And("^The suggestion list should disappear$")
  public void theSuggestionListShouldDisappear() throws Throwable {
    assertThat(storeLocatorPage.suggestionListExists()).isFalse();
  }

  @And("^The result list should be retained$")
  public void theResultListShouldBeRetained() throws Throwable {
    assertThat(storeLocatorPage.resultListExists()).isTrue();
  }

  @When("^User selects \"([^\"]*)\" from suggestion list$")
  public void userSelectsFromSuggestionList(String selectedAddress) throws Throwable {
    Address address = mapHolder.get(Address.class);
    storeLocatorPage.enter(address);
    storeLocatorPage.selectFromSuggestionList(selectedAddress);

    // set the selected selectedAddress
    mapHolder.put(Address.class, new Address(selectedAddress));
    Thread.sleep(4000);
  }

  @Then("^User should be shown all available store location suggestions for the given address$")
  public void userShouldBeShownAllAvailableStoreLocationSuggestionsForTheGivenAddress() throws Throwable { // expected
    Address address = mapHolder.get(Address.class);
    List<StoreLocationSuggestion> expectedStoreLocationSuggestions = modelFactory.readListFromJson(address.getValue(), StoreLocationSuggestion.class);

    // actual
    List<StoreLocationSuggestion> actualStoreLocationSuggestions = storeLocatorPage.getStoreLocationSuggestions();

    // assert
    assertThat(actualStoreLocationSuggestions).hasSize(expectedStoreLocationSuggestions.size());
    assertThat(actualStoreLocationSuggestions).containsAll(expectedStoreLocationSuggestions);
  }

  @When("^User selects city from quick search$")
  public void userSelectsCityFromQuickSearch() throws Throwable {
    Address address = mapHolder.get(Address.class);
    storeLocatorPage.selectCity(address);
  }

  @Then("^User should be shown all available store locations$")
  public void userShouldBeShownAllAvailableStoreLocations() throws Throwable {
    // expected
    Address address = mapHolder.get(Address.class);
    List<StoreLocationResult> expectedStoreLocationResults = modelFactory.readListFromJson(address.getValue(), StoreLocationResult.class);

    // actual
    List<StoreLocationResult> actualStoreLocationResults = storeLocatorPage.getStoreLocationResult();

    // assert
    assertThat(actualStoreLocationResults).hasSize(expectedStoreLocationResults.size());
    assertThat(actualStoreLocationResults).containsAll(expectedStoreLocationResults);
  }

  /*@Given("^Geolcation is disabled$")
  public void geolcationIsDisabled() throws Throwable {
    storeLocatorPage.geolocationDisabled();
  }*/

  @Then("^User should be shown a message \"([^\"]*)\" with title \"([^\"]*)\"$")
  public void userShouldBeShownAMessageWithTitle(String message, String title) throws Throwable {
    assertThat(storeLocatorPage.getInvalidLocationMessage()).isEqualToIgnoringCase(message);
    assertThat(storeLocatorPage.getInvalidLocationTitle()).isEqualToIgnoringCase(title);
  }
}
