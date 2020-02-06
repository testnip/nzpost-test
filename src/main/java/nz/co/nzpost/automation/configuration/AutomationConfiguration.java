package nz.co.nzpost.automation.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@ComponentScan("nz.co.nzpost.automation, cucumber.runtime.java.spring")
@EnableRetry
@EnableAspectJAutoProxy
@EnableAutoConfiguration
public class AutomationConfiguration {

  @Bean
  private RestTemplate getRestTemplate() {
    RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
    List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
    interceptors.add(new LoggingRequestInterceptor());
    restTemplate.setInterceptors(interceptors);

    return restTemplate;
  }
}
