package nz.co.nzpost.automation.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class AddressDetails {
  @JsonProperty("address_line_1")
  private String addressLine;

  @JsonProperty("suburb")
  private String suburb;

  @JsonProperty("city")
  private String city;

  @JsonProperty("post_code")
  private String postCode;

  public String getAddressLine() {
    return addressLine;
  }

  public String getSuburb() {
    return suburb;
  }

  public String getCity() {
    return city;
  }

  public String getPostCode() {
    return postCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AddressDetails that = (AddressDetails) o;
    return Objects.equals(addressLine, that.addressLine) &&
      Objects.equals(suburb, that.suburb) &&
      Objects.equals(city, that.city) &&
      Objects.equals(postCode, that.postCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(addressLine, suburb, city, postCode);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("AddressDetails{");
    sb.append("addressLine='").append(addressLine).append('\'');
    sb.append(", suburb='").append(suburb).append('\'');
    sb.append(", city='").append(city).append('\'');
    sb.append(", postCode='").append(postCode).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
