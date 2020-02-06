package nz.co.nzpost.automation.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Scope("cucumber-glue")
public class GeolocationDisabled extends HashMap<String, Object> {

  public <T> T get(String type) {
    return (T) (super.get(type));
  }
}
