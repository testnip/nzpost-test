package nz.co.nzpost.automation.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

import static java.lang.String.format;

@Component
public class JsonWriter {

  private final ObjectMapper objectMapper;

  @Autowired
  public JsonWriter(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  public void write(File file, Object object) {
    try {
      objectMapper.writeValue(file, object);
    } catch (IOException e) {
      throw new IllegalStateException(format("Unable to write object %s to file %s", object, file), e);
    }
  }
}
