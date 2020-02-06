package nz.co.nzpost.automation.page;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import nz.co.nzpost.automation.annotations.Page;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import static com.codeborne.selenide.Selenide.$;

@Page
public class ParcelForYouDateTimePage {

  private final String host;

  @Autowired
  public ParcelForYouDateTimePage(@Value("${host}") String host) {
    this.host = host;
  }

  public void selectFromDateCard() {
    SelenideElement test = $(".day-of-week");
    if (test.innerText().equals("Saturday")) {
      $(".redelivery-date-selection-cell-1").click();
    } else {
      $(".redelivery-date-selection-cell-0").click();
    }
  }

  public void selectFromMorningCard() {
    $(".redelivery-time-selection-cell-daytime").click();
  }

  public void selectFromEveningCard() {
    JavascriptExecutor je = (JavascriptExecutor) WebDriverRunner.getWebDriver();
    SelenideElement selectEveningCard = $(".redelivery-time-selection-evening");
    je.executeScript("arguments[0].scrollIntoView(true);", selectEveningCard);
    selectEveningCard.click();
  }

  public String getDateText() {
    return $(".redelivery-date-selection-cell.checked").getText().replaceAll("\n", " ");
  }

  public String getTimeText() {
    return $(".redelivery-time-selection-cell.checked").getText().replaceAll("\n", " ").replace("FREE", "").trim().replace("$ 5.00", "");
  }

  public void clickNext() {
    JavascriptExecutor je = (JavascriptExecutor) WebDriverRunner.getWebDriver();
    WebElement clickNext = $(".redelivery-date-selection-submit.next-button");
    je.executeScript("arguments[0].scrollIntoView(true);", clickNext);
    clickNext.click();
  }

  public String getAuthorisedLeaveHeading() {
    return $(".autotest-redelivery-atl-header").getText();
  }
}
