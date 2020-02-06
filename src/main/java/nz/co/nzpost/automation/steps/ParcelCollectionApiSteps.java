package nz.co.nzpost.automation.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nz.co.nzpost.automation.api.ParcelCollectionApiClientV1;
import nz.co.nzpost.automation.domain.Address;
import nz.co.nzpost.automation.domain.GetParcelCollectionResponse;
import nz.co.nzpost.automation.domain.MapHolder;
import nz.co.nzpost.automation.domain.ParcelCollectionLocation;
import nz.co.nzpost.automation.io.ModelFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ParcelCollectionApiSteps {

  private final MapHolder mapHolder;
  private final ParcelCollectionApiClientV1 parcelCollectionApiClientV1;
  private final ModelFactory modelFactory;

  @Autowired
  public ParcelCollectionApiSteps(MapHolder mapHolder, ParcelCollectionApiClientV1 parcelCollectionApiClientV1, ModelFactory modelFactory) {
    this.mapHolder = mapHolder;
    this.parcelCollectionApiClientV1 = parcelCollectionApiClientV1;
    this.modelFactory = modelFactory;
  }

  @When("^I call parcel collection endpoint to get parcel collection points$")
  public void iCallParcelCollectionEndpointToGetParcelCollectionPoints() throws Throwable {
    Address address = mapHolder.get(Address.class);
    GetParcelCollectionResponse<List<ParcelCollectionLocation>> getParcelCollectionResponse = parcelCollectionApiClientV1.get(address);
    mapHolder.put(GetParcelCollectionResponse.class, getParcelCollectionResponse);
  }

  @Then("^I should be shown list of parcel collections$")
  public void iShouldBeShownListOfParcelCollections() throws Throwable {
    Address address = mapHolder.get(Address.class);
    GetParcelCollectionResponse expectedGetParcelCollectionResponse = modelFactory.readFromJson(address.getValue(), GetParcelCollectionResponse.class);
    GetParcelCollectionResponse getParcelCollectionResponse = mapHolder.get(GetParcelCollectionResponse.class);
    getParcelCollectionResponse.getData();
    getParcelCollectionResponse.get();
    assertThat(getParcelCollectionResponse.get()).isEqualTo(expectedGetParcelCollectionResponse.get());
  }
}

