package nz.co.nzpost.automation.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.co.nzpost.automation.domain.Address;
import nz.co.nzpost.automation.domain.MapHolder;
import nz.co.nzpost.automation.domain.RedeliveryDetails;
import nz.co.nzpost.automation.domain.TrackingNumber;
import nz.co.nzpost.automation.io.ModelFactory;
import nz.co.nzpost.automation.page.ParcelForYouOriginalDeliveryAddressPage;
import nz.co.nzpost.automation.page.ParcelForYouPage;
import org.springframework.beans.factory.annotation.Autowired;

import static com.codeborne.selenide.Selenide.title;
import static org.assertj.core.api.Assertions.assertThat;

public class ParcelForYouOriginalDeliveryAddressSteps {

  private final ParcelForYouPage parcelForYouPage;
  private final ParcelForYouOriginalDeliveryAddressPage originalDeliveryAddressPage;
  private final MapHolder mapHolder;
  private final ModelFactory modelFactory;
  private final RedeliveryDetails redeliveryDetails;

  @Autowired
  public ParcelForYouOriginalDeliveryAddressSteps(RedeliveryDetails redeliveryDetails, ParcelForYouPage parcelForYouPage, ParcelForYouOriginalDeliveryAddressPage originalDeliveryAddressPage, MapHolder mapHolder, ModelFactory modelFactory) {
    this.parcelForYouPage = parcelForYouPage;
    this.mapHolder = mapHolder;
    this.modelFactory = modelFactory;
    this.originalDeliveryAddressPage = originalDeliveryAddressPage;
    this.redeliveryDetails = redeliveryDetails;
  }

  @Given("^User is on original delivery address page$")
  public void userIsOnOriginalDeliveryAddressPage() throws Throwable {
    /*mapHolder.put(RedeliveryDetails.class, redeliveryDetails);
    parcelForYouPage.gotoOriginalDeliveryAddressPage(mapHolder);*/
    parcelForYouPage.clickNext();
  }

  @And("^User selects \"([^\"]*)\" from available addresses suggestion list$")
  public void userSelectsFromAvailableAddressesSuggestionList(String selectAddress) throws Throwable {
    Address address = mapHolder.get(Address.class);
    originalDeliveryAddressPage.enter(address);
    originalDeliveryAddressPage.selectFromPrimaryAddressList(selectAddress);
  }

  @When("^User submit address$")
  public void userSubmitAddress() throws Throwable {
    originalDeliveryAddressPage.pressEnter();
    originalDeliveryAddressPage.pressEnter();
  }

  @Then("^User should be shown a message \"([^\"]*)\"$")
  public void userShouldBeShownAMessage(String message) throws Throwable {
    assertThat(originalDeliveryAddressPage.getInvalidAddressMessage()).isEqualToIgnoringCase(message);
  }

  @Then("^User should be shown redelivery options available for parcel$")
  public void userShouldBeShownRedeliveryOptionsAvailableForParcel() throws Throwable {
    assertThat(originalDeliveryAddressPage.getRedeliverSameAddress()).isEqualToIgnoringCase("Redeliver to the same address");
    assertThat(originalDeliveryAddressPage.getRedeliverDifferentAddress()).isEqualToIgnoringCase("Redeliver to a different address");
    assertThat(originalDeliveryAddressPage.getRedeliverCollectionPoint()).isEqualToIgnoringCase("Redeliver to a collection point");
  }

  @And("^User enters address \"([^\"]*)\"$")
  public void userEntersAddress(String invalidAddress) throws Throwable {
    Address address = new Address(invalidAddress);
    mapHolder.put(Address.class, address);
    mapHolder.get(Address.class);
    originalDeliveryAddressPage.enter(address);
  }

  @When("^User submit address using button$")
  public void userSubmitAddressUsingButton() throws Throwable {
    originalDeliveryAddressPage.clickNext();
  }

  @And("^redeliver to same address option should be selected$")
  public void redeliverToSameAddressOptionShouldBeSelected() throws Throwable {
    Thread.sleep(500);
    assertThat(originalDeliveryAddressPage.enabledRedeliverSameAddress()).isTrue();
  }

  @Given("^User has tracking number \"([^\"]*)\"$")
  public void userHasTrackingNumber(String number) throws Throwable {
    mapHolder.put(RedeliveryDetails.class, redeliveryDetails);
    parcelForYouPage.trackingNumber(number, mapHolder);
  }
}

