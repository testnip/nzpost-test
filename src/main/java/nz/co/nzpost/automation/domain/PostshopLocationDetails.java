package nz.co.nzpost.automation.domain;

import java.util.List;
import java.util.Objects;

public class PostshopLocationDetails {
  private String name;
  private String address;
  private String distance;
  private String phone;
  private OpeningHour openingHours;
  private List<String> services;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getDistance() {
    return distance;
  }

  public void setDistance(String distance) {
    this.distance = distance;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public OpeningHour getOpeningHours() {
    return openingHours;
  }

  public void setOpeningHours(OpeningHour openingHours) {
    this.openingHours = openingHours;
  }

  public List<String> getServices() {
    return services;
  }

  public void setServices(List<String> services) {
    this.services = services;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PostshopLocationDetails that = (PostshopLocationDetails) o;
    return Objects.equals(name, that.name) &&
      Objects.equals(address, that.address) &&
      Objects.equals(distance, that.distance) &&
      Objects.equals(phone, that.phone) &&
      Objects.equals(openingHours, that.openingHours) &&
      Objects.equals(services, that.services);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, address, distance, phone, openingHours, services);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("PostshopLocationDetails{");
    sb.append("name='").append(name).append('\'');
    sb.append(", address='").append(address).append('\'');
    sb.append(", distance='").append(distance).append('\'');
    sb.append(", phone='").append(phone).append('\'');
    sb.append(", openingHours=").append(openingHours);
    sb.append(", services=").append(services);
    sb.append('}');
    return sb.toString();
  }
}
