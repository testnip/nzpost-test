package nz.co.nzpost.automation.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.co.nzpost.automation.domain.MapHolder;
import nz.co.nzpost.automation.domain.RedeliveryDetails;
import nz.co.nzpost.automation.io.ModelFactory;
import nz.co.nzpost.automation.page.ParcelForYouNewlDeliveryAddressPage;
import nz.co.nzpost.automation.page.ParcelForYouOriginalDeliveryAddressPage;
import nz.co.nzpost.automation.page.ParcelForYouPage;
import nz.co.nzpost.automation.page.ParcelForYouRedeliveryOptionsPage;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class ParcelForYouNewDeliveryAddressSteps {

  private final ParcelForYouPage parcelForYouPage;
  private final ParcelForYouOriginalDeliveryAddressPage originalDeliveryAddressPage;
  private final ParcelForYouRedeliveryOptionsPage redeliveryOptionsPage;
  private final ParcelForYouNewlDeliveryAddressPage newlDeliveryAddressPage;
  private final MapHolder mapHolder;
  private final ModelFactory modelFactory;
  private final RedeliveryDetails redeliveryDetails;

  @Autowired
  public ParcelForYouNewDeliveryAddressSteps(RedeliveryDetails redeliveryDetails, ParcelForYouPage parcelForYouPage, ParcelForYouOriginalDeliveryAddressPage originalDeliveryAddressPage, ParcelForYouRedeliveryOptionsPage redeliveryOptionsPage, ParcelForYouNewlDeliveryAddressPage newlDeliveryAddressPage, MapHolder mapHolder, ModelFactory modelFactory) {
    this.parcelForYouPage = parcelForYouPage;
    this.mapHolder = mapHolder;
    this.modelFactory = modelFactory;
    this.originalDeliveryAddressPage = originalDeliveryAddressPage;
    this.redeliveryOptionsPage = redeliveryOptionsPage;
    this.newlDeliveryAddressPage = newlDeliveryAddressPage;
    this.redeliveryDetails = redeliveryDetails;
  }

  @Given("^User is on new delivery address page$")
  public void userIsOnNewDeliveryAddressPage() throws Throwable {
    mapHolder.put(RedeliveryDetails.class, redeliveryDetails);
    parcelForYouPage.gotoOriginalDeliveryAddressPage(mapHolder);
    originalDeliveryAddressPage.gotoRedeliveryOptionPage(mapHolder);
    redeliveryOptionsPage.gotoNewDeliveryAddressPage();
  }

  @Then("^User should be shown redelivery option page$")
  public void userShouldBeShownRedeliveryOptionPage() throws Throwable {
    assertThat(newlDeliveryAddressPage.getRedeliveryOptionsHeading()).isEqualToIgnoringCase("Redelivery Options");
  }

  @When("^User submit new address using button$")
  public void userSubmitNewAddressUsingButton() throws Throwable {
    newlDeliveryAddressPage.clickNext();
  }
}

