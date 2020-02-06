package nz.co.nzpost.automation.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class GetParcelCollectionResponse<T> {

  @JsonProperty("status")
  private String status;

  @JsonProperty("code")
  private String code;

  @JsonProperty("data")
  private Map<String, List<ParcelCollectionLocation>> data;

  @JsonProperty("locations")
  private List<ParcelCollectionLocation> parcelCollectionLocation;

  public String getStatus() {
    return status;
  }

  public String getCode() {
    return code;
  }

  public Object getData() {
    return data;
  }

  public List<ParcelCollectionLocation> get() {
    this.parcelCollectionLocation = data.get("locations");
    return parcelCollectionLocation;
  }
}
