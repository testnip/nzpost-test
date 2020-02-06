package nz.co.nzpost.automation.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@Scope("cucumber-glue")
public class MapHolder extends HashMap<Class, Object> {

  public <T> T get(Class<T> type) {
    return (T) (super.get(type));
  }
}
