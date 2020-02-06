package nz.co.nzpost.automation.io;

import com.google.common.io.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static java.lang.String.format;

@Component
public class ImageReader {

  private final FileReader fileReader;

  @Autowired
  public ImageReader(FileReader fileReader) {
    this.fileReader = fileReader;
  }

  public BufferedImage read(String path) throws IOException {
    InputStream inputStream = fileReader.read(path);
    return ImageIO.read(inputStream);
  }

  public BufferedImage readWithDefault(String path, BufferedImage defaultImage) throws IOException {
    InputStream inputStream = fileReader.read(path);
    if (inputStream != null) {
      return ImageIO.read(inputStream);
    }

    // create the image using the default image
    String pathInTarget = Thread.currentThread().getContextClassLoader().getResource(".").getFile() + path;
    String newFilePath = pathInTarget.replace("target/test-classes", "src/main/resources");
    File file = new File(newFilePath);
    Files.createParentDirs(file);
    file.createNewFile();
    ImageIO.write(defaultImage, "png", file);

    throw new IllegalArgumentException(format("No image found with provided path %s so the default image has been created!", path));
  }
}
