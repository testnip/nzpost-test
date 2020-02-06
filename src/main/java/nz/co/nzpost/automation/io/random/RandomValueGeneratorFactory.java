package nz.co.nzpost.automation.io.random;

public class RandomValueGeneratorFactory {
  public RandomValueGenerator create() {
    return new DefaultRandomValueGenerator();
  }
}
