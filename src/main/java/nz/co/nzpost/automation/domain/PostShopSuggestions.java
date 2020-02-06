package nz.co.nzpost.automation.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class PostShopSuggestions {
  private final String name;
  private final String address;

  @JsonCreator
  public PostShopSuggestions(
    @JsonProperty("name")
      String name,

    @JsonProperty("address")
      String address
  ) {
    this.name = name;
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PostShopSuggestions that = (PostShopSuggestions) o;
    return Objects.equals(name, that.name) &&
      Objects.equals(address, that.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, address);
  }
}



