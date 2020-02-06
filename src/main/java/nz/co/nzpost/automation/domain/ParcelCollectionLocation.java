package nz.co.nzpost.automation.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParcelCollectionLocation {
  @JsonProperty("company_name")
  private String companyName;

  @JsonProperty("lat")
  private Double latitude;

  @JsonProperty("lng")
  private Double longitude;

  @JsonProperty("phone")
  private String phone;

  @JsonProperty("full_address")
  private String fullAddress;

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("partner")
  private String partner;

  @JsonProperty("hours")
  private List<HoursEntity> hours = Lists.newArrayList();

  @JsonProperty("distance_in_m")
  private Integer distanceInMeter;

  @JsonProperty("address_details")
  private AddressDetails addressDetails;

  public String getCompanyName() {
    return companyName;
  }

  public Double getLatitude() {
    return latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public String getPhone() {
    return phone;
  }

  public String getFullAddress() {
    return fullAddress;
  }

  public Integer getId() {
    return id;
  }

  public String getPartner() {
    return partner;
  }

  public List<HoursEntity> getHours() {
    return hours;
  }

  public Integer getDistanceInMeter() {
    return distanceInMeter;
  }

  public AddressDetails getAddressDetails() {
    return addressDetails;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ParcelCollectionLocation that = (ParcelCollectionLocation) o;
    return Objects.equals(companyName, that.companyName) &&
      Objects.equals(latitude, that.latitude) &&
      Objects.equals(longitude, that.longitude) &&
      Objects.equals(phone, that.phone) &&
      Objects.equals(fullAddress, that.fullAddress) &&
      Objects.equals(id, that.id) &&
      Objects.equals(partner, that.partner) &&
      Objects.equals(hours, that.hours) &&
      Objects.equals(distanceInMeter, that.distanceInMeter) &&
      Objects.equals(addressDetails, that.addressDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(companyName, latitude, longitude, phone, fullAddress, id, partner, hours, distanceInMeter, addressDetails);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ParcelCollectionLocation{");
    sb.append("companyName='").append(companyName).append('\'');
    sb.append(", latitude=").append(latitude);
    sb.append(", longitude=").append(longitude);
    sb.append(", phone='").append(phone).append('\'');
    sb.append(", fullAddress='").append(fullAddress).append('\'');
    sb.append(", id=").append(id);
    sb.append(", partner='").append(partner).append('\'');
    sb.append(", hours=").append(hours);
    sb.append(", distanceInMeter=").append(distanceInMeter);
    sb.append(", addressDetails=").append(addressDetails);
    sb.append('}');
    return sb.toString();
  }

  public static final class ParcelCollectionLocationBuilder {
    private String companyName;
    private Double latitude;
    private Double longitude;
    private String phone;
    private String fullAddress;
    private Integer id;
    private String partner;
    private List<HoursEntity> hours = Lists.newArrayList();
    private Integer distanceInMeter;
    private AddressDetails addressDetails;

    private ParcelCollectionLocationBuilder() {
    }

    public static ParcelCollectionLocationBuilder aLocation() {
      return new ParcelCollectionLocationBuilder();
    }

    public ParcelCollectionLocationBuilder companyName(String companyName) {
      this.companyName = companyName;
      return this;
    }

    public ParcelCollectionLocationBuilder latitude(Double latitude) {
      this.latitude = latitude;
      return this;
    }

    public ParcelCollectionLocationBuilder longitude(Double longitude) {
      this.longitude = longitude;
      return this;
    }

    public ParcelCollectionLocationBuilder phone(String phone) {
      this.phone = phone;
      return this;
    }

    public ParcelCollectionLocationBuilder fullAddress(String fullAddress) {
      this.fullAddress = fullAddress;
      return this;
    }

    public ParcelCollectionLocationBuilder id(Integer id) {
      this.id = id;
      return this;
    }

    public ParcelCollectionLocationBuilder partner(String partner) {
      this.partner = partner;
      return this;
    }

    public ParcelCollectionLocationBuilder hours(List<HoursEntity> hours) {
      this.hours = hours;
      return this;
    }

    public ParcelCollectionLocationBuilder distanceInMeter(Integer distanceInMeter) {
      this.distanceInMeter = distanceInMeter;
      return this;
    }

    public ParcelCollectionLocationBuilder addressDetails(AddressDetails addressDetails) {
      this.addressDetails = addressDetails;
      return this;
    }

    public ParcelCollectionLocation build() {
      ParcelCollectionLocation parcelCollectionLocation = new ParcelCollectionLocation();
      parcelCollectionLocation.phone = this.phone;
      parcelCollectionLocation.id = this.id;
      parcelCollectionLocation.latitude = this.latitude;
      parcelCollectionLocation.longitude = this.longitude;
      parcelCollectionLocation.hours = this.hours;
      parcelCollectionLocation.partner = this.partner;
      parcelCollectionLocation.distanceInMeter = this.distanceInMeter;
      parcelCollectionLocation.fullAddress = this.fullAddress;
      parcelCollectionLocation.companyName = this.companyName;
      parcelCollectionLocation.addressDetails = this.addressDetails;
      return parcelCollectionLocation;
    }

  }
}