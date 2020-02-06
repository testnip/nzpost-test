package nz.co.nzpost.automation.page;

import com.codeborne.selenide.Selenide;
import nz.co.nzpost.automation.annotations.Page;
import org.springframework.beans.factory.annotation.Value;

@Page
public class HomePage {

  private final String host;

  public HomePage(@Value("${host}") String host) {
    this.host = host;
  }

  public void open() {
    Selenide.open(host + "/apf");
  }
}
