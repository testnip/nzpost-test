package nz.co.nzpost.automation.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.co.nzpost.automation.api.PostshopLocationsApiClient;
import nz.co.nzpost.automation.api.PostshopLocationsApiClientV2;
import nz.co.nzpost.automation.api.PostshopSuggestionsApiClient;
import nz.co.nzpost.automation.api.responses.GetPostshopLocationsResponse;
import nz.co.nzpost.automation.api.responses.GetPostshopSuggestionsResponse;
import nz.co.nzpost.automation.domain.*;
import nz.co.nzpost.automation.io.ModelFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PostshopLocattionsApiSteps {

  private final MapHolder mapHolder;
  private final PostshopLocationsApiClient postshopLocationsApiClient;
  private final PostshopSuggestionsApiClient postshopSuggestionsApiClient;
  private final PostshopLocationsApiClientV2 postshopLocationsApiClientV2;
  private final ModelFactory modelFactory;

  @Autowired
  public PostshopLocattionsApiSteps(MapHolder mapHolder, PostshopLocationsApiClient postshopLocationsApiClient, ModelFactory modelFactory, PostshopSuggestionsApiClient postshopSuggestionsApiClient, PostshopLocationsApiClientV2 postshopLocationsApiClientV2) {
    this.mapHolder = mapHolder;
    this.postshopLocationsApiClient = postshopLocationsApiClient;
    this.postshopSuggestionsApiClient = postshopSuggestionsApiClient;
    this.postshopLocationsApiClientV2 = postshopLocationsApiClientV2;
    this.modelFactory = modelFactory;
  }

  @Given("^I have an address \"([^\"]*)\"$")
  public void iHaveAnAddress(String address) throws Throwable {
    mapHolder.put(Address.class, new Address(address));
  }

  @When("^I call postshop locations endpoint to get a list of postshop locations$")
  public void iCallPostshopLocationsEndpointToGetAListOfPostshopLocations() throws Throwable {
    Address address = mapHolder.get(Address.class);
    GetPostshopLocationsResponse<List<PostshopLocation>> getPostshopLocationsResponse = postshopLocationsApiClient.get(address);
    mapHolder.put(GetPostshopLocationsResponse.class, getPostshopLocationsResponse);
  }

  @Then("^I should get a list of postshop locations for that address$")
  public void iShouldGetAListOfPostshopLocationsForThatAddress() throws Throwable {
    Address address = mapHolder.get(Address.class);
    GetPostshopLocationsResponse expectedGetPostshopLocationsResponse = modelFactory.readFromJson(address.getValue(), GetPostshopLocationsResponse.class);
    GetPostshopLocationsResponse getPostshopLocationsResponse = mapHolder.get(GetPostshopLocationsResponse.class);

    assertThat(getPostshopLocationsResponse.get()).isEqualTo(expectedGetPostshopLocationsResponse.get());
  }

  @Then("^I should get an error for invalid characters$")
  public void iShouldGetAnErrorForInvalidCharacters() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException();
  }

  @When("^I call suggestions endpoint to get a list of suggested address$")
  public void iCallSuggestionsEndpointToGetAListOfSuggestedAddress() throws Throwable {
    Address address = mapHolder.get(Address.class);
    GetPostshopSuggestionsResponse<List<PostShopSuggestions>> getPostshopSuggestionsResponse = postshopSuggestionsApiClient.get(address);
    mapHolder.put(GetPostshopSuggestionsResponse.class, getPostshopSuggestionsResponse);
  }

  @Then("^I should get a list of suggested address for that address$")
  public void iShouldGetAListOfSuggestedAddressForThatAddress() throws Throwable {
    Address address = mapHolder.get(Address.class);
    GetPostshopSuggestionsResponse expectedGetPostshopSuggestionsResponse = modelFactory.readFromJson(address.getValue(), GetPostshopSuggestionsResponse.class);
    GetPostshopSuggestionsResponse getPostshopSuggestionsResponse = mapHolder.get(GetPostshopSuggestionsResponse.class);
    assertThat(getPostshopSuggestionsResponse.get()).isEqualTo(expectedGetPostshopSuggestionsResponse.get());
  }

  @Given("^I have a map coordinates for address \"([^\"]*)\"$")
  public void iHaveAMapCoordinates(String addressCoordinates) throws Throwable {
    Query<MapExtent> query = modelFactory.readFromJsonWithGenericType(addressCoordinates, Query.class, MapExtent.class);
    mapHolder.put(Query.class, query);
  }

  @When("^I call postshop locations coordinates endpoint to get a list of postshop locations$")
  public void iCallPostshopLocationsCoordinatesEndpointToGetAListOfPostshopLocations() throws Throwable {
    Query query = mapHolder.get(Query.class);
    GetPostshopLocationsResponse<List<PostshopLocation>> getPostshopLocationsResponse = postshopLocationsApiClientV2.get(query);
    mapHolder.put(GetPostshopLocationsResponse.class, getPostshopLocationsResponse);
  }

  @Then("^I should get a list of postshop locations for that coordinates address \"([^\"]*)\"$")
  public void iShouldGetAListOfPostshopLocationsForThatCoordinates(String addressCoordinates) throws Throwable {
    GetPostshopLocationsResponse expectedGetPostshopLocationsResponse = modelFactory.readFromJson(addressCoordinates, GetPostshopLocationsResponse.class);
    GetPostshopLocationsResponse getPostshopLocationsResponse = mapHolder.get(GetPostshopLocationsResponse.class);
    assertThat(getPostshopLocationsResponse.get()).isEqualTo(expectedGetPostshopLocationsResponse.get());
  }

  @Given("^I have a geolocation coordinates for address \"([^\"]*)\"$")
  public void iHaveAGeolocationCoordinatesForAddress(String addressCoordinates) throws Throwable {
    Query<Geolocation> query = modelFactory.readFromJsonWithGenericType(addressCoordinates, Query.class, Geolocation.class);
    mapHolder.put(Query.class, query);
  }

  @Given("^I have a keyword for address \"([^\"]*)\"$")
  public void iHaveAKeywordForAddress(String addressKeyword) throws Throwable {
    Query query = modelFactory.readFromJson(addressKeyword, Query.class);
    mapHolder.put(Query.class, query);
  }
}
