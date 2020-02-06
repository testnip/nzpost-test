package nz.co.nzpost.automation.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class HoursEntity {
  @JsonProperty("day")
  private Integer day;

  @JsonProperty("open")
  private String open;

  @JsonProperty("close")
  private String close;

  @JsonProperty("closed")
  private boolean closed;

  public Integer getDay() {
    return day;
  }

  public String getOpen() {
    return open;
  }

  public String getClose() {
    return close;
  }

  public boolean isClosed() {
    return closed;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    HoursEntity that = (HoursEntity) o;
    return closed == that.closed &&
      Objects.equals(day, that.day) &&
      Objects.equals(open, that.open) &&
      Objects.equals(close, that.close);
  }

  @Override
  public int hashCode() {
    return Objects.hash(day, open, close, closed);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("HoursEntity{");
    sb.append("day=").append(day);
    sb.append(", open='").append(open).append('\'');
    sb.append(", close='").append(close).append('\'');
    sb.append(", closed=").append(closed);
    sb.append('}');
    return sb.toString();
  }
}
