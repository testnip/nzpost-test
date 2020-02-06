package nz.co.nzpost.automation.io;

import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class FileReader {
  public InputStream read(String path) {
    return Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
  }
}
