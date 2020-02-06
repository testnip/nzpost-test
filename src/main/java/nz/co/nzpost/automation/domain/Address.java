package nz.co.nzpost.automation.domain;

import java.util.Objects;

public class Address {
  private final String value;

  public Address(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Address address = (Address) o;
    return Objects.equals(value, address.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }

  @Override
  public String toString() {
    return "Address{" +
      "value='" + value + '\'' +
      '}';
  }
}
