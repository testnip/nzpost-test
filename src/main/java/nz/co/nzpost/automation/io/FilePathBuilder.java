package nz.co.nzpost.automation.io;

import nz.co.nzpost.automation.external.CloudProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

@Component
public class FilePathBuilder {

  private final CloudProperties cloudProperties;

  @Autowired
  public FilePathBuilder(CloudProperties cloudProperties) {
    this.cloudProperties = cloudProperties;
  }

  public <T> String build(Class<T> classType, String type, String fileExtension) {

    return format("data/%s/%s.%s", classType.getSimpleName().toLowerCase(), sanitise(type.toLowerCase()), fileExtension.toLowerCase());
  }

  public String buildImagePath(String classType, String type, String fileExtension) {
    if (cloudProperties.isEnabled()) {
      return format("data/cloud/%s/%s.%s", classType.toLowerCase(), sanitise(type.toLowerCase()), fileExtension.toLowerCase());
    }
    return format("data/%s/%s.%s", classType.toLowerCase(), sanitise(type.toLowerCase()), fileExtension.toLowerCase());
  }

  protected String sanitise(String value) {
    String sanitisedValue = value;
    Pattern pt = Pattern.compile("[^a-zA-Z0-9\\s@_]");
    Matcher match = pt.matcher(sanitisedValue);
    while (match.find()) {
      String s = match.group();
      sanitisedValue = sanitisedValue.replaceAll("\\" + s, "-");
      sanitisedValue = sanitisedValue.replaceAll("@", "%40");
    }
    return sanitisedValue;
  }

}
