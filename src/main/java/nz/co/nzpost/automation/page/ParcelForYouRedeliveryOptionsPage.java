package nz.co.nzpost.automation.page;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import nz.co.nzpost.automation.annotations.Page;
import nz.co.nzpost.automation.browser.Browser;
import org.openqa.selenium.JavascriptExecutor;
import org.springframework.beans.factory.annotation.Autowired;

import static com.codeborne.selenide.Selenide.$;

@Page
public class ParcelForYouRedeliveryOptionsPage {

  private final Browser browser;

  @Autowired
  public ParcelForYouRedeliveryOptionsPage(Browser browser) {
    this.browser = browser;
  }

  public void clickNext() {
    $(".autotest-redelivery-options-next-button").click();
  }

  public void selectRedeliverSameAddress() {
    $(".autotest-same-address.redelivery-option").click();
  }

  public void selectRedeliverDifferentAddress() {
    $("span.redelivery-option.autotest-different-address").click();
  }

  public void selectRedeliverCollectionPoint() {
    $("span.autotest-parcel-collect").click();
  }

  public void clickPrevious() {
    $(".previous-button").pressTab().click();
  }

  public String getNewDeliveryAddressTitle() {
    return $(".autotest-redelivery-address-title").getText();
  }

  public String getContactDetailsHeading() {
    return $(".title-color-red-nz-post").getText();
  }

  public String getParcelCollectHeading() {
    return $(".title-color-red-nz-post").getText();
  }

  public void gotoNewDeliveryAddressPage() {
    this.selectRedeliverDifferentAddress();
    this.clickNext();
  }

  public void gotoParcelCollectionPage() {
    this.selectRedeliverCollectionPoint();
    this.clickNext();
  }

  public void gotoContactDetailsPage() throws Throwable {
    this.selectRedeliverSameAddress();
    this.clickNext();
    Thread.sleep(500);
  }
}
