package nz.co.nzpost.automation.page;

import nz.co.nzpost.automation.annotations.Page;
import nz.co.nzpost.automation.browser.Browser;
import org.springframework.beans.factory.annotation.Autowired;

import static com.codeborne.selenide.Selenide.$;

@Page
public class ParcelForYouCollectionPointPage {

  private final Browser browser;

  @Autowired
  public ParcelForYouCollectionPointPage(Browser browser) {
    this.browser = browser;
  }

  public void selectParcelCollectionLocation() {
    $(".redelivery-pcd-selection-cell-0").click();
  }

  public void clickNext() {
    $(".redelivery-pcd-selection-submit.next-button").click();
  }

  public void gotoContactDetailsPage() throws Throwable {
    this.selectParcelCollectionLocation();
    this.clickNext();
    Thread.sleep(1000);
  }
}
