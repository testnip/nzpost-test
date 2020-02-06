package nz.co.nzpost.automation.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import nz.co.nzpost.automation.domain.Address;
import nz.co.nzpost.automation.domain.ParcelAddress;
import nz.co.nzpost.automation.domain.PostshopLocation;

import java.util.List;
import java.util.Map;

public class GetParcelAddressResponse<T> {


  @JsonProperty("status")
  private String status;

  @JsonProperty("code")
  private String code;

  @JsonProperty("data")
  private Map<String, List<ParcelAddress>> data;

  @JsonProperty("addresses")
  private List<ParcelAddress> parcelAddress;

  public String getStatus() {
    return status;
  }

  public String getCode() {
    return code;
  }

  public Object getData() {
    return data;
  }

  public List<ParcelAddress> get() {
    this.parcelAddress = data.get("addresses");
    return parcelAddress;
  }
}
