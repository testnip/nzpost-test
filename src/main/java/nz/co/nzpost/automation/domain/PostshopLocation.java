package nz.co.nzpost.automation.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostshopLocation {
  @JsonProperty("name")
  private String name;

  @JsonProperty("lat")
  private Double latitude;

  @JsonProperty("lng")
  private Double longitude;

  @JsonProperty("phone")
  private String phone;

  @JsonProperty("address")
  private String address;

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("description")
  private String description;

  @JsonProperty("type")
  private String type;

  @JsonProperty("hours")
  private List<HoursEntity> hours;

  @JsonProperty("services")
  private List<String> services;

  @JsonProperty("distance_in_m")
  private Integer distanceInMeters;

  @JsonProperty("address_details")
  private AddressDetails addressDetails;

  public String getName() {
    return name;
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

  public String getAddress() {
    return address;
  }

  public Integer getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public String getType() {
    return type;
  }

  public List<HoursEntity> getHours() {
    return hours;
  }

  public List<String> getServices() {
    return services;
  }

  public Integer getDistanceInMeters() {
    return distanceInMeters;
  }

  public AddressDetails getAddressDetails() {
    return addressDetails;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PostshopLocation that = (PostshopLocation) o;
    return Objects.equals(name, that.name) &&
      Objects.equals(latitude, that.latitude) &&
      Objects.equals(longitude, that.longitude) &&
      Objects.equals(phone, that.phone) &&
      Objects.equals(address, that.address) &&
      Objects.equals(id, that.id) &&
      Objects.equals(description, that.description) &&
      Objects.equals(type, that.type) &&
      Objects.equals(hours, that.hours) &&
      Objects.equals(services, that.services) &&
      Objects.equals(distanceInMeters, that.distanceInMeters) &&
      Objects.equals(addressDetails, that.addressDetails);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, latitude, longitude, phone, address, id, description, type, hours, services, distanceInMeters, addressDetails);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("PostshopLocation{");
    sb.append("name='").append(name).append('\'');
    sb.append(", latitude=").append(latitude);
    sb.append(", longitude=").append(longitude);
    sb.append(", phone='").append(phone).append('\'');
    sb.append(", address='").append(address).append('\'');
    sb.append(", id=").append(id);
    sb.append(", description='").append(description).append('\'');
    sb.append(", type='").append(type).append('\'');
    sb.append(", hours=").append(hours);
    sb.append(", services=").append(services);
    sb.append(", distanceInMeters=").append(distanceInMeters);
    sb.append(", addressDetails=").append(addressDetails);
    sb.append('}');
    return sb.toString();
  }

  public static final class PostshopLocationBuilder {
    private String name;
    private Double latitude;
    private Double longitude;
    private String phone;
    private String address;
    private Integer id;
    private String description;
    private String type;
    private List<HoursEntity> hours;
    private List<String> services;
    private Integer distanceInMeters;
    private AddressDetails addressDetails;

    private PostshopLocationBuilder() {
    }

    public static PostshopLocationBuilder aPostshopLocation() {
      return new PostshopLocationBuilder();
    }

    public PostshopLocationBuilder name(String name) {
      this.name = name;
      return this;
    }

    public PostshopLocationBuilder latitude(Double latitude) {
      this.latitude = latitude;
      return this;
    }

    public PostshopLocationBuilder longitude(Double longitude) {
      this.longitude = longitude;
      return this;
    }

    public PostshopLocationBuilder phone(String phone) {
      this.phone = phone;
      return this;
    }

    public PostshopLocationBuilder address(String address) {
      this.address = address;
      return this;
    }

    public PostshopLocationBuilder id(Integer id) {
      this.id = id;
      return this;
    }

    public PostshopLocationBuilder description(String description) {
      this.description = description;
      return this;
    }

    public PostshopLocationBuilder type(String type) {
      this.type = type;
      return this;
    }

    public PostshopLocationBuilder hours(List<HoursEntity> hours) {
      this.hours = hours;
      return this;
    }

    public PostshopLocationBuilder services(List<String> services) {
      this.services = services;
      return this;
    }

    public PostshopLocationBuilder distanceInMeters(Integer distanceInMeters) {
      this.distanceInMeters = distanceInMeters;
      return this;
    }

    public PostshopLocationBuilder addressDetails(AddressDetails addressDetails) {
      this.addressDetails = addressDetails;
      return this;
    }

    public PostshopLocation build() {
      PostshopLocation postshopLocation = new PostshopLocation();
      postshopLocation.distanceInMeters = this.distanceInMeters;
      postshopLocation.type = this.type;
      postshopLocation.latitude = this.latitude;
      postshopLocation.addressDetails = this.addressDetails;
      postshopLocation.address = this.address;
      postshopLocation.hours = this.hours;
      postshopLocation.services = this.services;
      postshopLocation.phone = this.phone;
      postshopLocation.longitude = this.longitude;
      postshopLocation.id = this.id;
      postshopLocation.description = this.description;
      postshopLocation.name = this.name;
      return postshopLocation;
    }
  }
}
