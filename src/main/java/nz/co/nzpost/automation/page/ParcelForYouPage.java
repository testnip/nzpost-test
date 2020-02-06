package nz.co.nzpost.automation.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import nz.co.nzpost.automation.annotations.Page;
import nz.co.nzpost.automation.domain.MapHolder;
import nz.co.nzpost.automation.domain.RedeliveryDetails;
import nz.co.nzpost.automation.domain.TrackingNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static com.codeborne.selenide.Selenide.$;

@Page
public class ParcelForYouPage {

  private final String host;
  private final MapHolder mapHolder;


  @Autowired
  public ParcelForYouPage(@Value("${host}") String host, MapHolder mapHolder) {
    this.host = host;
    this.mapHolder = mapHolder;
  }

  public void open() {
    Selenide.open(host + "/parcel-for-you");
  }

  public void enter(TrackingNumber trackingNumber) {
    SelenideElement trackingNumberInput = $("input.autotest-parcel-tracking-number");
    trackingNumberInput.sendKeys(trackingNumber.getNumber());
  }

  public void pressEnter() {
    SelenideElement trackingNumberInput = $("input.autotest-parcel-tracking-number").pressEnter();
  }

  public void clickNext() {
    $(".tracking-number-submit").click();
  }

  public String getInvalidTrackingNumberMessage() {
    return $(".autotest-tracking-number-form-text-error-message span").getText();
  }

  public String getNotEligibleForRedeliveryMessage() {
    return $(".autotest-toast-notification-message").getText();
  }

  public String getParcelDetailsHeading() {
    return $(".autotest-delivery-details-header").getText();
  }

  public void trackingNumber(String number, MapHolder mapHolder) {
    this.open();
    TrackingNumber trackingNumber = new TrackingNumber(number);
    mapHolder.put(TrackingNumber.class, trackingNumber);
    mapHolder.get(TrackingNumber.class);
    RedeliveryDetails redeliveryDetails = mapHolder.get(RedeliveryDetails.class);
    redeliveryDetails.setTrackingNumber(trackingNumber.getNumber());
    mapHolder.put(RedeliveryDetails.class, redeliveryDetails);
    this.enter(trackingNumber);
  }
  
  public void gotoOriginalDeliveryAddressPage(MapHolder mapHolder) {
    this.clickNext();
  }
}
