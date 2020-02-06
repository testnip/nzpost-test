package nz.co.nzpost.automation.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import nz.co.nzpost.automation.domain.PostShopSuggestions;

import java.util.List;

public class GetPostshopSuggestionsResponse<T> {

  @JsonProperty("suggestions")
  private List<PostShopSuggestions> postshopSuggestions;

  public List<PostShopSuggestions> get() {
    return postshopSuggestions;
  }
}
