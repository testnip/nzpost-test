package nz.co.nzpost.automation.domain;

import java.util.List;
import java.util.Objects;

public class AddressResultMessage {
  private String heading;
  private List<String> paragraphs;

  public String getHeading() {
    return heading;
  }

  public void setHeading(String heading) {
    this.heading = heading;
  }

  public List<String> getParagraphs() {
    return paragraphs;
  }

  public void setParagraphs(List<String> paragraphs) {
    this.paragraphs = paragraphs;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AddressResultMessage that = (AddressResultMessage) o;
    return Objects.equals(heading, that.heading) &&
      Objects.equals(paragraphs, that.paragraphs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(heading, paragraphs);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("AddressResultMessage{");
    sb.append("heading='").append(heading).append('\'');
    sb.append(", paragraphs=").append(paragraphs);
    sb.append('}');
    return sb.toString();
  }
}
