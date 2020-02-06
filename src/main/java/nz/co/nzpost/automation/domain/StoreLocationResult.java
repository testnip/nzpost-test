package nz.co.nzpost.automation.domain;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class StoreLocationResult {
  private String title;
  private String address;
  private String direction;
  private String phone;
  private List<String> services;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
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
    StoreLocationResult that = (StoreLocationResult) o;

    // sort both lists
    sortStrings(services);
    sortStrings(that.services);

    return Objects.equals(title, that.title) &&
      Objects.equals(address, that.address) &&
      Objects.equals(direction, that.direction) &&
      Objects.equals(phone, that.phone) &&
      Objects.equals(services, that.services);
  }

  private void sortStrings(List<String> list) {
    if (list != null) {
      list.sort(Comparator.naturalOrder());
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, address, direction, phone, services);
  }

  @Override
  public String toString() {
    return "StoreLocationResult{" +
      "title='" + title + '\'' +
      ", address='" + address + '\'' +
      ", direction='" + direction + '\'' +
      ", phone='" + phone + '\'' +
      ", services=" + services +
      '}';
  }
}
