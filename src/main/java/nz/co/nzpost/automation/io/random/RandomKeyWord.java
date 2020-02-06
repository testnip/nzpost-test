package nz.co.nzpost.automation.io.random;

public enum RandomKeyWord {
  RANDOM_TEXT_NUMBER("#RANDOM_TEXT_NUMBER#"),
  RANDOM_TEXT("#RANDOM_TEXT#"),
  RANDOM_CAPITAL_TEXT("#RANDOM_CAPITAL_TEXT#"),
  RANDOM_EMAIL("#RANDOM_EMAIL#"),
  RANDOM_NUMBER("#RANDOM_NUMBER#"),
  RANDOM_DATE("#RANDOM_DATE#"),
  REGEX_RANDOM_TEXT_LENGTH("#RANDOM_TEXT_([0-9]*)#"),
  REGEX_RANDOM_CAPITAL_TEXT_LENGTH("#RANDOM_CAPITAL_TEXT_([0-9]*)#"),
  REGEX_RANDOM_NUMBER_LENGTH("#RANDOM_NUMBER_([0-9]*)#");

  private final String code;

  RandomKeyWord(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
