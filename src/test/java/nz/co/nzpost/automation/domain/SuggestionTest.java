package nz.co.nzpost.automation.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SuggestionTest {

  @Test
  public void equals() {
    Suggestion suggestion = new Suggestion();
    suggestion.setAddress("16 Cornwall Pl");
    suggestion.setPostcode("2113");

    Suggestion anotherSuggestion = new Suggestion();
    anotherSuggestion.setAddress("16 Cornwall Pl");
    anotherSuggestion.setPostcode("2113");

    // when
    boolean equals = suggestion.equals(anotherSuggestion);

    // then
    assertThat(equals).isTrue();
  }

  @Test
  public void equalsFalse() {
    Suggestion suggestion = new Suggestion();
    suggestion.setAddress("16 Cornwall Pl");
    suggestion.setPostcode("2113");

    Suggestion anotherSuggestion = new Suggestion();
    anotherSuggestion.setAddress("16 Cornwall Pl");
    anotherSuggestion.setPostcode("2114");

    // when
    boolean equals = suggestion.equals(anotherSuggestion);

    // then
    assertThat(equals).isFalse();
  }

}