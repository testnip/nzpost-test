package nz.co.nzpost.automation.domain;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class RedeliveryDetails {
  private String trackingNumber;
  private String redeliverAddress;
  private String redeliverDifferentAddress;
  private String redeliverCollectionAddress;
  private String redeliverDate;
  private String redeliverTime;
  private String redeliveryInstructions;

  public String getTrackingNumber() {
    return trackingNumber;
  }

  public void setTrackingNumber(String trackingNumber) {
    this.trackingNumber = trackingNumber;
  }

  public String getRedeliverAddress() {
    return redeliverAddress;
  }

  public void setRedeliverAddress(String redeliverSameAddress) {
    this.redeliverAddress = redeliverSameAddress;
  }

  public String getRedeliverDifferentAddress() {
    return redeliverDifferentAddress;
  }

  public void setRedeliverDifferentAddress(String redeliverDifferentAddress) {
    this.redeliverDifferentAddress = redeliverDifferentAddress;
  }

  public String getRedeliverDate() {
    return redeliverDate;
  }

  public void setRedeliverDate(String redeliverDate) {
   this.redeliverDate = redeliverDate;
  }

  public String getRedeliverTime() {
    return redeliverTime;
  }

  public void setRedeliverTime(String redeliverTime) {
    this.redeliverTime = redeliverTime;
  }

  public String getRedeliverCollectionAddress() {
    return redeliverCollectionAddress;
  }

  public void setRedeliverCollectionAddress(String redeliverCollectionAddress) {
    this.redeliverCollectionAddress = redeliverCollectionAddress;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RedeliveryDetails that = (RedeliveryDetails) o;
    return Objects.equals(trackingNumber, that.trackingNumber) &&
      Objects.equals(redeliverAddress, that.redeliverAddress) &&
      Objects.equals(redeliverDifferentAddress, that.redeliverDifferentAddress) &&
      Objects.equals(redeliverCollectionAddress, that.redeliverCollectionAddress) &&
      Objects.equals(redeliverDate, that.redeliverDate) &&
      Objects.equals(redeliverTime, that.redeliverTime) &&
      Objects.equals(redeliveryInstructions, that.redeliveryInstructions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(trackingNumber, redeliverAddress, redeliverDifferentAddress, redeliverCollectionAddress, redeliverDate, redeliverTime, redeliveryInstructions);
  }
}

