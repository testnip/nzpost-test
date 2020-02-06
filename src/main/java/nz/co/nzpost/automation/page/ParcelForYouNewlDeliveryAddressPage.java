package nz.co.nzpost.automation.page;

import com.codeborne.selenide.SelenideElement;
import nz.co.nzpost.automation.annotations.Page;
import nz.co.nzpost.automation.browser.Browser;
import nz.co.nzpost.automation.domain.Address;
import nz.co.nzpost.automation.domain.MapHolder;
import nz.co.nzpost.automation.domain.RedeliveryDetails;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Page
public class ParcelForYouNewlDeliveryAddressPage {

  private final String host;
  private final Browser browser;
  private final MapHolder mapHolder;

  @Autowired
  public ParcelForYouNewlDeliveryAddressPage(@Value("${host}") String host, Browser browser, MapHolder mapHolder, ParcelForYouOriginalDeliveryAddressPage originalDeliveryAddressPage1) {
    this.host = host;
    this.browser = browser;
    this.mapHolder = mapHolder;
  }

  public void enter(Address address) throws InterruptedException {
    SelenideElement addressSearchInput = $(".autotest-search-field-input");
    browser.sendKeysToInputAtSpeed(address.getValue(), addressSearchInput, 100);
  }

  public void selectFromPrimaryAddressList(String lookupAddress) {
    Spliterator<SelenideElement> spliterator = $$(".autotest-suggestion").spliterator();
    StreamSupport.stream(spliterator, false).filter(addressContainer -> {
        List<WebElement> spans = addressContainer.findElementsByClassName("autotest-address-suggestion");
        String address = StringUtils.join(spans.stream().map(WebElement::getText).collect(Collectors.toList()), ", ");
        return lookupAddress.equalsIgnoreCase(StringUtils.removeEnd(address, ", "));
      }
    ).findFirst().get().click();
  }

  public void clickNext() {
    $(".autotest-redelivery-address-next-button").click();
  }

  public String getRedeliveryOptionsHeading() {
    return $(".autotest-delivery-details-header").getText();
  }

  public void gotoContactPage(MapHolder mapHolder) throws Throwable {
    Address address = new Address("122 Kerwyn Avenue, East Tamaki, Auckland 2013");
    mapHolder.put(Address.class, address);
    mapHolder.get(Address.class);
    RedeliveryDetails redeliveryDetails = mapHolder.get(RedeliveryDetails.class);
    redeliveryDetails.setRedeliverDifferentAddress(address.getValue());
    this.enter(address);
    Thread.sleep(200);
    this.selectFromPrimaryAddressList("122 Kerwyn Avenue, East Tamaki, Auckland 2013");
    this.clickNext();
  }
}