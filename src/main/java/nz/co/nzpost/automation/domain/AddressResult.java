package nz.co.nzpost.automation.domain;

public class AddressResult {
  private final String address;
  private final String postcode;

  public AddressResult(String address, String postcode) {

    this.address = address;
    this.postcode = postcode;
  }

  public String getAddress() {
    return address;
  }

  public String getPostcode() {
    return postcode;
  }
}
