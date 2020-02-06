package nz.co.nzpost.automation.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ParcelAddress {
  @JsonProperty("full_address")
  private String fullAddress;

  @JsonProperty("address_id")
  private String addressId;

  @JsonProperty("dpid")
  private String dpid;

  public String getFullAddress() {
    return fullAddress;
  }

  public String getAddressId() {
    return addressId;
  }

  public String getDpid() {
    return dpid;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ParcelAddress that = (ParcelAddress) o;
    return Objects.equals(fullAddress, that.fullAddress) &&
      Objects.equals(addressId, that.addressId) &&
      Objects.equals(dpid, that.dpid);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fullAddress, addressId, dpid);
  }

  public static final class ParcelAddressBuilder {
    private String addressId;
    private String fullAddress;
    private String dpid;

    private ParcelAddressBuilder() {
    }

    public static ParcelAddressBuilder anAddress() {
      return new ParcelAddressBuilder();
    }

    public ParcelAddressBuilder addressId(String addressId) {
      this.addressId = addressId;
      return this;
    }

    public ParcelAddressBuilder dpid(String dpid) {
      this.dpid = dpid;
      return this;
    }

    public ParcelAddressBuilder fullAddress(String fullAddress) {
      this.fullAddress = fullAddress;
      return this;
    }

    public ParcelAddress build() {
      ParcelAddress address = new ParcelAddress();
      address.fullAddress = this.fullAddress;
      address.addressId = this.addressId;
      address.dpid = this.dpid;
      return address;
    }
  }
}