package nz.co.nzpost.automation.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import nz.co.nzpost.automation.annotations.Page;
import nz.co.nzpost.automation.browser.Browser;
import nz.co.nzpost.automation.domain.Address;
import nz.co.nzpost.automation.domain.AddressResult;
import nz.co.nzpost.automation.domain.AddressResultMessage;
import nz.co.nzpost.automation.domain.AddressResultType;
import nz.co.nzpost.automation.exception.ElementNotFoundException;
import nz.co.nzpost.automation.screenshot.ScreenshotTaker;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Page
public class AddressSearchPage {

  private static final String ADDRESS_SEPARATOR = ", ";

  private final String host;
  private final Browser browser;
  private final ScreenshotTaker screenshotTaker;

  @Autowired
  public AddressSearchPage(@Value("${host}") String host, Browser browser, ScreenshotTaker screenshotTaker) {
    this.host = host;
    this.browser = browser;
    this.screenshotTaker = screenshotTaker;
  }

  public void open() {
    Selenide.open(host + "/tools/address-postcode-finder");
  }

  public void enter(Address address) throws InterruptedException {
    SelenideElement addressSearchInput = $("#address-search__search-input-1");
    browser.sendKeysToInputAtSpeed(address.getValue(), addressSearchInput, 50);
  }

  public void pressEnter() {
    SelenideElement addressSearchInput = $("#address-search__search-input-1").pressEnter();
  }

  public AddressResult getAddressResult() {
    String addressText = getAddressText();
    String postcode = $(".address-results__result-item-postcode").getText();
    return new AddressResult(addressText, postcode);
  }

  private String getAddressText() {
    List<WebElement> spans = $(".address-results__result-item-address-container").findElementsByTagName("span");
    return StringUtils.join(spans.stream().map(WebElement::getText).collect(Collectors.toList()), ADDRESS_SEPARATOR);
  }

  public AddressResultType getAddressResultType() {
    if ($(".info-box--success").exists()) {
      return AddressResultType.VALID;
    } else if ($(".info-box--failure").exists()) {
      return AddressResultType.INVALID;
    } else if ($(".info-box--standard").exists()) {
      return AddressResultType.INFO;
    }
    throw new ElementNotFoundException("Address Result Type icon not found!");
  }

  public String getResultMessage() {
    return $(".text-area").getText().replaceAll("\n", "");
  }

  public void selectFromPrimaryPostcodeList(String postcode) {
    $$(".address-search__result-item-postcode").find(Condition.matchText(postcode)).click();
  }

  public void selectFromSecondaryPostcodeList(String postcode) {
    $$(".o-details-list-box-1__li-link").find(Condition.matchText(postcode)).click();
  }

  public void clickSearchIcon() {
    $("#address-search__search-button-1").click();
  }

  public List<AddressResultMessage> getAddressResultMessages() {
    List<AddressResultMessage> addressResultMessages = new ArrayList<>();
    ElementsCollection addressResults = $$(".address-results__detail");
    for (SelenideElement addressResult : addressResults) {
      SelenideElement headingElement = addressResult.find(".address-results__detail-title");
      ElementsCollection paragraphElements = addressResult.findAll(".address-results__detail-paragraph");

      AddressResultMessage addressResultMessage = new AddressResultMessage();
      addressResultMessage.setHeading(headingElement.getText());

      List<String> paragraphs = new ArrayList();
      addressResultMessage.setParagraphs(paragraphs);
      for (SelenideElement paragraphElement : paragraphElements) {
        paragraphs.add(paragraphElement.getText().replace("\n",""));
      }

      addressResultMessages.add(addressResultMessage);
    }

    return addressResultMessages;
  }

  public String getStandardMailDeliveryHeading() {
    return $(".address-results__days-text").getText();
  }

  public List<String> getStandardMailDeliveryDays() {
    return StreamSupport.stream($$(".address-results__days-item--t").spliterator(), false).map(SelenideElement::getText).collect(Collectors.toList());
  }

  public void selectFromPrimaryAddressList(String lookupAddress) {
    Spliterator<SelenideElement> spliterator = $$(".address-search__result-item-address").spliterator();
    StreamSupport.stream(spliterator, false).filter(addressContainer -> {
        List<WebElement> spans = addressContainer.findElementsByTagName("span");
        String address = StringUtils.join(spans.stream().map(WebElement::getText).collect(Collectors.toList()), ", ");
        return lookupAddress.equalsIgnoreCase(StringUtils.removeEnd(address, ", "));
      }
    ).findFirst().get().click();
  }

  public boolean hasCopyThisAddressButton() {
    return $(".address-results__copy-address-button").exists();
  }

  public BufferedImage getMapImage() {
    WebElement map = $(".app-layout__main-right");
    return screenshotTaker.takeScreenShot(map);
  }

  public void clickSuggestAnAddressButton() {
    $(".o-button-text-icon--blue-white").click();
  }

  public void clickMapButton() {
    $(".esri-basemap-toggle").click();
  }

  public boolean disabledSearchIcon() {
    return $("#address-search__search-button-1").isEnabled();
  }

  public void clickLabelButton() {
    $(".satellite-toggles__child-checkbox-label").click();
  }

  public String getSuggestAnAddressHeading() {
    return $(".page-header-title").getText();
  }
}
