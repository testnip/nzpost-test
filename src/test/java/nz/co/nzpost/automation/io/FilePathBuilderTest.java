package nz.co.nzpost.automation.io;

import nz.co.nzpost.automation.external.CloudProperties;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class FilePathBuilderTest {

  private FilePathBuilder filePathBuilder;
  private CloudProperties cloudProperties;

  @Before
  public void setUp() {
    cloudProperties = mock(CloudProperties.class);
    filePathBuilder = new FilePathBuilder(cloudProperties);
  }

  @Test
  public void build() {
  }

  @Test
  public void buildImagePath() {
  }

  @Test
  public void sanitiseAmpersand() {
    String value = "Greytown NZ Post & Kiwibank";

    // when
    String sanitisedValue = filePathBuilder.sanitise(value);

    // then
    assertThat(sanitisedValue).isEqualTo("Greytown NZ Post - Kiwibank");
  }

  @Test
  public void sanitiseComma() {
    String value = "Point England, Auckland";

    // when
    String sanitisedValue = filePathBuilder.sanitise(value);

    // then
    assertThat(sanitisedValue).isEqualTo("Point England- Auckland");
  }
}