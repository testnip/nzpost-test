package nz.co.nzpost.automation.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.co.nzpost.automation.domain.*;
import nz.co.nzpost.automation.io.ModelFactory;
import nz.co.nzpost.automation.page.AddressSearchPage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

public class AddressSearchSteps {

  private final AddressSearchPage addressSearchPage;
  private final MapHolder mapHolder;
  private final ModelFactory modelFactory;

  @Autowired
  public AddressSearchSteps(AddressSearchPage addressSearchPage, MapHolder mapHolder, ModelFactory modelFactory) {
    this.addressSearchPage = addressSearchPage;
    this.mapHolder = mapHolder;
    this.modelFactory = modelFactory;
  }

  @Given("^User is on address search page$")
  public void userIsOnAddressSearchPage() throws Throwable {
    addressSearchPage.open();
  }

  @Given("^User has address \"([^\"]*)\"$")
  public void userHasAddress(String postcode) throws Throwable {
    Address address = new Address(postcode);
    mapHolder.put(Address.class, address);
  }

  @When("^User performs address search$")
  public void userPerformsAddressSearch() throws Throwable {
    Address address = mapHolder.get(Address.class);
    addressSearchPage.enter(address);
    addressSearchPage.pressEnter();
  }

  @Then("^User should see \"([^\"]*)\" and \"([^\"]*)\" in the address result$")
  public void userShouldSeeAndInTheAddressResult(String postcode, String address) throws Throwable {
    AddressResult addressResult = addressSearchPage.getAddressResult();
    assertThat(addressResult.getAddress()).isEqualToIgnoringCase(address);
    assertThat(addressResult.getPostcode()).isEqualTo(postcode);
  }

  @And("^User should see icon \"([^\"]*)\" and message \"([^\"]*)\"$")
  public void userShouldSeeIconAndMessage(AddressResultType addressResultType, String message) throws Throwable {
    assertThat(addressSearchPage.getAddressResultType()).isEqualByComparingTo(addressResultType);
    assertThat(addressSearchPage.getResultMessage()).isEqualToIgnoringCase(message);
  }

  @When("^User selects \"([^\"]*)\" from primary available postcodes$")
  public void userSelectsFromPrimaryAvailablePostcodes(String postcode) throws Throwable {
    Address address = mapHolder.get(Address.class);
    addressSearchPage.enter(address);
    addressSearchPage.selectFromPrimaryPostcodeList(postcode);
  }

  @When("^User selects \"([^\"]*)\" from secondary available postcodes$")
  public void userSelectsFromSecondaryAvailablePostcodes(String postcode) throws Throwable {
    Address address = mapHolder.get(Address.class);
    addressSearchPage.enter(address);
    addressSearchPage.pressEnter();
    addressSearchPage.selectFromSecondaryPostcodeList(postcode);
  }

  @When("^User performs address search by search icon$")
  public void userPerformsAddressSearchBySearchIcon() throws Throwable {
    Address address = mapHolder.get(Address.class);
    addressSearchPage.enter(address);
    addressSearchPage.clickSearchIcon();
  }

  @And("^User should see additional information for valid postcode \"([^\"]*)\"$")
  public void userShouldSeeAdditionalInformationForValidPostcode(String messageType) throws Throwable {
    List<AddressResultMessage> expectedAddressResultMessages = modelFactory.readListFromJson(messageType, AddressResultMessage.class);
    assertThat(addressSearchPage.getAddressResultMessages()).isEqualTo(expectedAddressResultMessages);
  }

  @And("^User should see days standard mail delivery days \"([^\"]*)\"$")
  public void userShouldSeeDaysStandardMailDeliveryDays(List<String> days) throws Throwable {
    assertThat(addressSearchPage.getStandardMailDeliveryHeading()).isEqualToIgnoringCase("Days of delivery");
    assertThat(addressSearchPage.getStandardMailDeliveryDays()).containsExactlyElementsOf(days);
  }

  @When("^User selects \"([^\"]*)\" from primary available addresses$")
  public void userSelectsFromPrimaryAvailableAddresses(String selectAddress) throws Throwable {
    Address address = mapHolder.get(Address.class);
    addressSearchPage.enter(address);
    addressSearchPage.selectFromPrimaryAddressList(selectAddress);
  }

  @And("^User should see copy this address button$")
  public void userShouldSeeCopyThisAddressButton() throws Throwable {
    assertThat(addressSearchPage.hasCopyThisAddressButton()).isTrue();
  }

  @Given("^User has address \"([^\"]*)\" with preceding spaces$")
  public void userHasAddressWithPrecedingSpaces(String postcode) throws Throwable {
    userHasAddress(format("   %s", postcode));
  }


  @When("^User starts address search with special characters$")
  public void userStartsAddressSearchWithSpecialCharacters() throws Throwable {
    Address address = mapHolder.get(Address.class);
    addressSearchPage.enter(address);
  }


  @And("^search icon should be disabled$")
  public void searchIconShouldBeDisabled() throws Throwable {
    addressSearchPage.disabledSearchIcon();
    assertThat(addressSearchPage.disabledSearchIcon()).isFalse();
  }
}
