package nz.co.nzpost.automation.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import nz.co.nzpost.automation.domain.PostshopLocation;

import java.util.List;

public class GetPostshopLocationsResponse<T> {

  @JsonProperty("locations")
  private List<PostshopLocation> postshopLocations;

  public List<PostshopLocation> get() {
    return postshopLocations;
  }
}
