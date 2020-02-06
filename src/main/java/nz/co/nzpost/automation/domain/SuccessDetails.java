package nz.co.nzpost.automation.domain;

import java.util.Objects;

public class SuccessDetails {
  private String trackingNumber;
  private String redeliverAddress;
  private String redeliverDate;

  public String getTrackingNumber() {
    return trackingNumber;
  }

  public void setTrackingNumber(String trackingNumber) {
    this.trackingNumber = trackingNumber;
  }

  public String getRedeliverAddress() {
    return redeliverAddress;
  }

  public void setRedeliverAddress(String redeliverAddress) {
    this.redeliverAddress = redeliverAddress;
  }

  public String getRedeliverDate() {
    return redeliverDate;
  }

  public void setRedeliverDate(String redeliverDate) {
    this.redeliverDate = redeliverDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SuccessDetails that = (SuccessDetails) o;
    return Objects.equals(trackingNumber, that.trackingNumber) &&
      Objects.equals(redeliverAddress, that.redeliverAddress) &&
      Objects.equals(redeliverDate, that.redeliverDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(trackingNumber, redeliverAddress, redeliverDate);
  }
}

