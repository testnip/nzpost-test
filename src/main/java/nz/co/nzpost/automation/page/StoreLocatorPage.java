package nz.co.nzpost.automation.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import nz.co.nzpost.automation.annotations.Page;
import nz.co.nzpost.automation.browser.Browser;
import nz.co.nzpost.automation.domain.Address;
import nz.co.nzpost.automation.domain.StoreLocationResult;
import nz.co.nzpost.automation.domain.StoreLocationSuggestion;
import nz.co.nzpost.automation.exception.ElementNotFoundException;
import nz.co.nzpost.automation.screenshot.ScreenshotTaker;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Page
public class StoreLocatorPage {

  private final String host;
  private final ScreenshotTaker screenshotTaker;
  private final Browser browser;

  @Autowired
  public StoreLocatorPage(@Value("${host}") String host, ScreenshotTaker screenshotTaker, Browser browser) {
    this.host = host;
    this.screenshotTaker = screenshotTaker;
    this.browser = browser;
  }

  public void open() {
    Selenide.open(host + "/postshop-locator");
  }

  public void enter(Address address) throws InterruptedException {
    Thread.sleep(2000);
    SelenideElement addressSearchInput = $(".search-field-input");
    browser.sendKeysToInputAtSpeed(address.getValue(), addressSearchInput, 400);
  }

  public void pressEnter() {
    $(".search-field-input").pressEnter();
  }

  public void clearSearch() {
    $(".search-end-adornment-button").click();
  }

  public String getSearchValue() {
    return $(".search-field-input").getValue();
  }

  public Boolean suggestionListExists() throws InterruptedException {
    Thread.sleep(500);
    return $(".list-section").exists();
  }

  public Boolean resultListExists() {
    if (!$(".result-list-item").exists()) {
      throw new ElementNotFoundException("Result list does not exist!");
    }
    return true;
  }

  public List<StoreLocationSuggestion> getStoreLocationSuggestions() {
    List<StoreLocationSuggestion> suggestions = new ArrayList<>();
    ElementsCollection storeLocationContainers = $$(".location-content");

    if (storeLocationContainers.isEmpty()) {
      throw new ElementNotFoundException();
    }

    for (SelenideElement storeLocationContainer : storeLocationContainers) {
      // suggestion
      StoreLocationSuggestion suggestion = new StoreLocationSuggestion();

      // title
      String title = storeLocationContainer.find(".location-name-text").getText();
      suggestion.setTitle(title);

      // address
      SelenideElement addressElement = storeLocationContainer.find(".location-address-text");
      if (addressElement.exists()) {
        suggestion.setAddress(addressElement.getText());
      }

      // add to list
      suggestions.add(suggestion);
    }
    return suggestions;
  }

  public void selectFromSuggestionList(String location) {
    $$(".location-suggestion-item").stream()
      .filter(locationSuggestionItem -> location.equalsIgnoreCase(locationSuggestionItem.find(".location-name-text").getText()))
      .findFirst()
      .get()
      .click();
  }

  public List<StoreLocationResult> getStoreLocationResult() {
    List<StoreLocationResult> results = new ArrayList<>();
    ElementsCollection storeLocationResultContainers = $$(".postshop-card");

    if (storeLocationResultContainers.isEmpty()) {
      throw new ElementNotFoundException();
    }

    for (int i = 0; i < storeLocationResultContainers.size(); i++) {
      SelenideElement storeLocationContainer = storeLocationResultContainers.get(i);

      // suggestion
      StoreLocationResult result = new StoreLocationResult();

      // title
      String title = storeLocationContainer.find(".postshop-card-title").getText();
      result.setTitle(title);

      // address
      String address = storeLocationContainer.find(".postshop-card-address").getText();
      result.setAddress(address);

      // direction
      String direction = storeLocationContainer.find(".postshop-card-directions-button").getAttribute("href");
      result.setDirection(direction);

      // phone
      String phone = storeLocationContainer.find(".postshop-card-phone-text").getText();
      result.setPhone(phone);

      // services
      if (storeLocationContainer.find(".postshop-card-services-container").exists()) {
        List<String> services = getServices(storeLocationContainer);
        result.setServices(services);
      }

      // add to list
      results.add(result);
    }
    return results;
  }

  private List<String> getServices(SelenideElement storeLocationContainer) {
      storeLocationContainer.find(".postshop-card-services-title").click();
      ElementsCollection serviceContainers = storeLocationContainer.findAll(".autotest-service");
      List<String> services = serviceContainers.stream()
        .map(serviceContainer -> {
          serviceContainer.waitUntil(Condition.visible, 5000);
          return serviceContainer.getText();
        })
        .collect(Collectors.toList());
      return services;
    }

  public void selectCity(Address address) {
    ElementsCollection cityButtons = $$(".locator-quick-search-button");
    cityButtons.stream().filter(cityButton -> address.getValue().trim().equalsIgnoreCase(cityButton.getText())).findFirst().get().click();
  }

  public BufferedImage getMapImage() {
    WebElement map = $(".gis-map-container");
    return screenshotTaker.takeScreenShot(map);
  }

  public void selectLocationIconMap(String locationId) {
    $("#postshop-icon-" + locationId).click();
  }

  public String getSelectedListValue() {
    return $(".selected-postshop .postshop-card-title").getText();
  }

  public String getInvalidLocationMessage() {
    return $(".not-found-card-body").getText();
  }

  public String getInvalidLocationTitle() {
    return $(".not-found-card-title").getText();
  }

}
