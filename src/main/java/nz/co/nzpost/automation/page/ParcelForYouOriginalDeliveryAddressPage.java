package nz.co.nzpost.automation.page;

import com.codeborne.selenide.SelenideElement;
import nz.co.nzpost.automation.annotations.Page;
import nz.co.nzpost.automation.browser.Browser;
import nz.co.nzpost.automation.domain.Address;
import nz.co.nzpost.automation.domain.MapHolder;
import nz.co.nzpost.automation.domain.RedeliveryDetails;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
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
public class ParcelForYouOriginalDeliveryAddressPage {

  private final String host;
  private final Browser browser;
  private final MapHolder mapHolder;

  @Autowired
  public ParcelForYouOriginalDeliveryAddressPage(@Value("${host}") String host, Browser browser, MapHolder mapHolder, RedeliveryDetails redeliveryDetails) {
    this.host = host;
    this.browser = browser;
    this.mapHolder = mapHolder;
  }

   public void enter(Address address) throws InterruptedException {
    SelenideElement addressSearchInput = $(".autotest-search-field-input");
    browser.sendKeysToInputAtSpeed(address.getValue(), addressSearchInput, 20);
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
    $(".autotest-delivery-details-next-button").click();
  }

  public void pressEnter() {
    SelenideElement addressInput = $(".autotest-search-field-input").pressEnter();
  }
  
  public String getInvalidAddressMessage() {
    return $(".autotest-toast-notification-message").getText();
  }

  public String getRedeliverSameAddress() {
    SelenideElement sameAddress = $("label.redelivery-option.autotest-same-address").find("span", 3);
    return sameAddress.getText();
  }

  public String getRedeliverDifferentAddress() {
    SelenideElement differentAddress = $("label.redelivery-option.autotest-different-address").find("span", 3);
    return differentAddress.getText();
  }

  public String getRedeliverCollectionPoint() {
    SelenideElement collectionPoint = $("label.autotest-parcel-collect").find("span", 3);
    return collectionPoint.getText();
  }

  public boolean enabledRedeliverSameAddress() {
    return $("input[value='sameAddress']").isSelected();
  }

  public void gotoRedeliveryOptionPage(MapHolder mapHolder) throws Throwable {
    Address address = new Address("14 Iwinuku Crescent, Wattle Downs, Auckland 2103");
    mapHolder.put(Address.class, address);
    mapHolder.get(Address.class);
    RedeliveryDetails redeliveryDetails = mapHolder.get(RedeliveryDetails.class);
    redeliveryDetails.setRedeliverAddress(address.getValue());
    mapHolder.put(RedeliveryDetails.class, redeliveryDetails);
    this.enter(address);
    Thread.sleep(200);
    this.selectFromPrimaryAddressList("14 Iwinuku Crescent, Wattle Downs, Auckland 2103");
    this.clickNext();
  }
}
