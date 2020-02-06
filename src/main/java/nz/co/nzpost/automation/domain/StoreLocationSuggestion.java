package nz.co.nzpost.automation.domain;

import java.util.Objects;

public class StoreLocationSuggestion {
  private String title;
  private String address;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StoreLocationSuggestion that = (StoreLocationSuggestion) o;
    return Objects.equals(title, that.title) &&
      Objects.equals(address, that.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, address);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("StoreLocationSuggestion{");
    sb.append("title='").append(title).append('\'');
    sb.append(", address='").append(address).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
