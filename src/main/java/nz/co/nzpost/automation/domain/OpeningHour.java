package nz.co.nzpost.automation.domain;

import java.util.Objects;

public class OpeningHour {
  private String day;
  private String openingTime;
  private String closingTime;

  public String getDay() {
    return day;
  }

  public void setDay(String day) {
    this.day = day;
  }

  public String getOpeningTime() {
    return openingTime;
  }

  public void setOpeningTime(String openingTime) {
    this.openingTime = openingTime;
  }

  public String getClosingTime() {
    return closingTime;
  }

  public void setClosingTime(String closingTime) {
    this.closingTime = closingTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    OpeningHour that = (OpeningHour) o;
    return Objects.equals(day, that.day) &&
      Objects.equals(openingTime, that.openingTime) &&
      Objects.equals(closingTime, that.closingTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(day, openingTime, closingTime);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("OpeningHour{");
    sb.append("day='").append(day).append('\'');
    sb.append(", openingTime='").append(openingTime).append('\'');
    sb.append(", closingTime='").append(closingTime).append('\'');
    sb.append('}');
    return sb.toString();
  }
}
