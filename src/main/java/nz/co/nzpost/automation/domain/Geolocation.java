package nz.co.nzpost.automation.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Geolocation {
  Double latitude;
  Double longitude;

  @JsonCreator
  public Geolocation(
    @JsonProperty("latitude")
      Double latitude,

    @JsonProperty("longitude")
      Double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public Double getLatitude() {
    return latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  @Override
  public String toString() {
    return "Geolocation{" +
      "latitude=" + latitude +
      ", longitude=" + longitude +
      '}';
  }
}
