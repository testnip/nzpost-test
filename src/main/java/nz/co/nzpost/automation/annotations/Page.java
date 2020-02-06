package nz.co.nzpost.automation.annotations;

import nz.co.nzpost.automation.exception.ElementNotFoundException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Component
@Retryable(value = {Throwable.class, ElementNotFoundException.class}, maxAttempts = 10, backoff = @Backoff(delay = 500))
public @interface Page {
}
