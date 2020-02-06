package nz.co.nzpost.automation.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Query<T> {
  private QueryType type;
  private T value;
  private Integer max;

  @JsonCreator
  public Query(
    @JsonProperty("type")
      QueryType type,

    @JsonProperty("value")
      T value,

    @JsonProperty("max")
      Integer max) {
    this.type = type;
    this.value = value;
    this.max = max;
  }

  public QueryType getType() {
    return type;
  }

  public T getValue() {
    return value;
  }

  public Integer getMax() {
    return max;
  }

  @Override
  public String toString() {
    return "Query{" +
      "type=" + type +
      ", value=" + value +
      '}';
  }
}
