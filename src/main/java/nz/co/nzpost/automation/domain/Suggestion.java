package nz.co.nzpost.automation.domain;

import java.util.Objects;

public class Suggestion {
  private String address;
  private String postcode;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPostcode() {
    return postcode;
  }

  public void setPostcode(String postcode) {
    this.postcode = postcode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Suggestion that = (Suggestion) o;
    return Objects.equals(address, that.address) &&
      Objects.equals(postcode, that.postcode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(address, postcode);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Suggestion{");
    sb.append("address='").append(address).append('\'');
    sb.append(", postcode='").append(postcode).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
