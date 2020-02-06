package nz.co.nzpost.automation.io.random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

public class DefaultRandomValueGenerator implements RandomValueGenerator {

  final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd-MM-yyyy-HH-mm-ss");

  @Override
  public String generateRandomValueIfRequired(String text) {
    if (StringUtils.isBlank(text)) {
      return text;
    }

    if (text.contains(RandomKeyWord.RANDOM_TEXT_NUMBER.getCode())) {
      text = text.replaceAll(RandomKeyWord.RANDOM_TEXT_NUMBER.getCode(), RandomStringUtils.randomAlphanumeric(20));
    }

    if (text.contains(RandomKeyWord.RANDOM_TEXT.getCode())) {
      text = text.replaceAll(RandomKeyWord.RANDOM_TEXT.getCode(), RandomStringUtils.randomAlphabetic(20));
    }

    if (text.contains(RandomKeyWord.RANDOM_CAPITAL_TEXT.getCode())) {
      text = text.replaceAll(RandomKeyWord.RANDOM_CAPITAL_TEXT.getCode(), RandomStringUtils.randomAlphabetic(20).toUpperCase());
    }

    if (text.contains(RandomKeyWord.RANDOM_NUMBER.getCode())) {
      text = text.replaceAll(RandomKeyWord.RANDOM_NUMBER.getCode(), RandomStringUtils.randomNumeric(20));
    }

    if (text.contains(RandomKeyWord.RANDOM_DATE.getCode())) {
      text = text.replaceAll(RandomKeyWord.RANDOM_DATE.getCode(), dateTimeFormatter.print(RandomUtils.nextLong(0, Long.MAX_VALUE)));
    }

    if (text.contains(RandomKeyWord.RANDOM_EMAIL.getCode())) {
      final String name = RandomStringUtils.randomAlphabetic(10);
      final String number = RandomStringUtils.randomNumeric(5);
      final String domain = RandomStringUtils.randomAlphabetic(10);
      final String extension = RandomStringUtils.randomAlphabetic(3);
      text = text.replaceAll(RandomKeyWord.RANDOM_EMAIL.getCode(), format("%s%s@%s.%s", name, number, domain, extension));
    }

    Integer textLength = getLength(text, RandomKeyWord.REGEX_RANDOM_TEXT_LENGTH);
    if (textLength > 0) {
      text = text.replaceAll(RandomKeyWord.REGEX_RANDOM_TEXT_LENGTH.getCode(), RandomStringUtils.randomAlphabetic(textLength));
    }

    Integer textCapitalLength = getLength(text, RandomKeyWord.REGEX_RANDOM_CAPITAL_TEXT_LENGTH);
    if (textCapitalLength > 0) {
      text = text.replaceAll(RandomKeyWord.REGEX_RANDOM_CAPITAL_TEXT_LENGTH.getCode(), RandomStringUtils.randomAlphabetic(textCapitalLength).toUpperCase());
    }

    Integer numberLength = getLength(text, RandomKeyWord.REGEX_RANDOM_NUMBER_LENGTH);
    if (numberLength > 0) {
      text = text.replaceAll(RandomKeyWord.REGEX_RANDOM_NUMBER_LENGTH.getCode(), RandomStringUtils.randomNumeric(numberLength));
    }

    return text;
  }

  private Integer getLength(String text, RandomKeyWord randomKeyWord) {
    Pattern pattern = Pattern.compile(randomKeyWord.getCode());
    Matcher matcher = pattern.matcher(text);

    if (matcher.find()) {
      return Integer.valueOf(matcher.group(1));
    }

    return -1;
  }

}
