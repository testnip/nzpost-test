package nz.co.nzpost.automation.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressSuggestion {
  private String fullName;
  private String city;
  private Boolean isMyAddress;
  private String email;
  private String street;
  private String suburb;
  private String comments;

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getSuburb() {
    return suburb;
  }

  public void setSuburb(String suburb) {
    this.suburb = suburb;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  @JsonProperty(value = "isMyAddress")
  public Boolean getMyAddress() {
    return isMyAddress;
  }

  public void setMyAddress(Boolean myAddress) {
    this.isMyAddress = myAddress;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }
}
