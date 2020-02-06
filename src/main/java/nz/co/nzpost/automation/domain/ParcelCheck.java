package nz.co.nzpost.automation.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParcelCheck {
  @JsonProperty("status")
  private String status;

  @JsonProperty("data")
  private String data;


  public String getData() {
    return data;
  }

  public String getStatus() {
    return status;
  }

  @Override
  public String toString() {
    return "ParcelCheck{" +
      "status='" + status + '\'' +
      ", data='" + data + '\'' +
      '}';
  }
  public static final class ParcelCheckBuilder {

    private String status;
    private String data;

    private ParcelCheckBuilder() {
    }

    public static ParcelCheckBuilder aParcelCheck() {
      return new ParcelCheckBuilder();
    }

    public ParcelCheckBuilder status(String status) {
      this.status = status;
      return this;
    }

    public ParcelCheckBuilder data(String data) {
      this.data = data;
      return this;
    }


    public ParcelCheck build() {
      ParcelCheck parcelCheck = new ParcelCheck();
      parcelCheck.status = this.status;
      parcelCheck.data = this.data;
      return parcelCheck;
    }
  }
}
