package nz.co.nzpost.automation.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.co.nzpost.automation.page.HomePage;
import org.springframework.beans.factory.annotation.Autowired;

public class HomePageSteps {

  private final HomePage homePage;

  @Autowired
  public HomePageSteps(HomePage homePage) {
    this.homePage = homePage;
  }

  @Given("^I have an address search url$")
  public void iHaveAnAddressSearchUrl() throws Throwable {
  }

  @When("^I visit the url$")
  public void iVisitTheUrl() throws Throwable {
    homePage.open();
  }

  @Then("^I should see the address search page$")
  public void iShouldSeeTheAddressSearchPage() throws Throwable {
  }
}
