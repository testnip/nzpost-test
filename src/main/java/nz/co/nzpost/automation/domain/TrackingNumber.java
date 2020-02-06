package nz.co.nzpost.automation.domain;

import java.util.Objects;

public class TrackingNumber {
  private String number;

  public TrackingNumber(String number) {
    this.number = number;
  }

  public String getNumber() {
    return number;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TrackingNumber that = (TrackingNumber) o;
    return Objects.equals(number, that.number);
  }

  @Override
  public int hashCode() {
    return Objects.hash(number);
  }
}


