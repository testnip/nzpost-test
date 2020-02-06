package nz.co.nzpost.automation.domain;

public class MapExtent {
  private Double latitude1;
  private Double longitude1;
  private Double latitude2;
  private Double longitude2;

  public MapExtent(Double latitude1, Double longitude1, Double latitude2, Double longitude2) {

    this.latitude1 = latitude1;
    this.longitude1 = longitude1;
    this.latitude2 = latitude2;
    this.longitude2 = longitude2;
  }

  public MapExtent() {
  }

  public Double getLatitude1() {
    return latitude1;
  }

  public Double getLongitude1() {
    return longitude1;
  }

  public Double getLatitude2() {
    return latitude2;
  }

  public Double getLongitude2() {
    return longitude2;
  }

  public void setLatitude1(Double latitude1) {
    this.latitude1 = latitude1;
  }

  public void setLongitude1(Double longitude1) {
    this.longitude1 = longitude1;
  }

  public void setLatitude2(Double latitude2) {
    this.latitude2 = latitude2;
  }

  public void setLongitude2(Double longitude2) {
    this.longitude2 = longitude2;
  }

  @Override
  public String toString() {
    return "MapExtent{" +
      "latitude1=" + latitude1 +
      ", longitude1=" + longitude1 +
      ", latitude2=" + latitude2 +
      ", longitude2=" + longitude2 +
      '}';
  }
}
