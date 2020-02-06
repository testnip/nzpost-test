package nz.co.nzpost.automation.io;

import com.google.common.io.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static java.lang.String.format;

@Component
public class ModelFactory {

  private final FilePathBuilder filePathBuilder;
  private final FileReader fileReader;
  private final JsonReader jsonReader;
  private final JsonWriter jsonWriter;

  @Autowired
  public ModelFactory(FilePathBuilder filePathBuilder, FileReader fileReader, JsonReader jsonReader, JsonWriter jsonWriter) {
    this.filePathBuilder = filePathBuilder;
    this.fileReader = fileReader;
    this.jsonReader = jsonReader;
    this.jsonWriter = jsonWriter;
  }

  public <T> T readFromJson(String type, Class<T> classType) {
    String path = filePathBuilder.build(classType, type, "json");
    InputStream inputStream = fileReader.read(path);
    return jsonReader.read(inputStream, classType);
  }

  public <T> List<T> readListFromJson(String type, Class<T> classType) {
    String path = filePathBuilder.build(classType, type, "json");
    InputStream inputStream = fileReader.read(path);
    return jsonReader.readList(inputStream, classType);
  }

  public <T> void writeJsonToFile(String type, Class<T> classType, T object) {
    String path = filePathBuilder.build(classType, type, "json");
    jsonWriter.write(getFile(path), object);
  }

  private File getFile(String path) {
    String targetPath = format("%s/target/test-classes/%s", System.getProperty("user.dir"), path);
    File file = new File(targetPath);
    try {
      Files.createParentDirs(file);
      file.createNewFile();
    } catch (IOException e) {
      throw new IllegalStateException(format("Unable to create a new file with path %s", path), e);
    }
    return file;
  }

  public <M> M readFromJsonWithGenericType(String type, Class<M> mainClass, Class<?> childClass) {
    String path = filePathBuilder.build(mainClass, type, "json");
    InputStream inputStream = fileReader.read(path);
    return jsonReader.read(inputStream, mainClass, childClass);
  }
}
