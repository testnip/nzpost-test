package nz.co.nzpost.automation.exception;

public class ElementNotFoundException extends RuntimeException {

  public ElementNotFoundException(String message) {
    super(message);
  }

  public ElementNotFoundException() {
    super();
  }
}
