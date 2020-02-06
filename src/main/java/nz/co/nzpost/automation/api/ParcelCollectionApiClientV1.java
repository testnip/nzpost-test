package nz.co.nzpost.automation.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import nz.co.nzpost.automation.domain.Address;
import nz.co.nzpost.automation.domain.GetParcelCollectionResponse;
import nz.co.nzpost.automation.domain.ParcelCollectionLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static java.lang.String.format;

@Component
public class ParcelCollectionApiClientV1 {

  private final RestTemplate restTemplate;
  private final ApiProperties apiProperties;
  private final ObjectMapper objectMapper;
  private final String scheme;
  private final String host;

  @Autowired
  public ParcelCollectionApiClientV1(RestTemplate restTemplate, ApiProperties apiProperties, ObjectMapper objectMapper) {
    this.restTemplate = restTemplate;
    this.apiProperties = apiProperties;
    this.objectMapper = objectMapper;


    String[] split = apiProperties.getHost().split("://");
    scheme = split[0];
    host = split[1];
  }

  public GetParcelCollectionResponse<List<ParcelCollectionLocation>> get(Address address) {
    MultiValueMap<String, String> uriVariables = new LinkedMultiValueMap<>();
    //uriVariables.get(address.getValue());
    uriVariables.add("count", "50");

    final UriComponents uriComponents = UriComponentsBuilder.newInstance()
      .scheme(scheme)
      .host(format("%s%s", host, apiProperties.getParcelCollectionUrl()))
      .path(format("%s/pcdlocations", address.getValue()))
      .queryParams(uriVariables)
      .build();

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    httpHeaders.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
    httpHeaders.add("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJuenBvc3Qtd2ViLXJlcGxhdGZvcm0tdGVzdCIsImlhdCI6MTUxNjIzOTAyMn0.-YJv6upFkpu_i5Ti70RSnqbWeNqeWPlx4yKgcnFhH6s");

    HttpEntity<Object> requestEntity = new HttpEntity<>(httpHeaders);

    ResponseEntity<GetParcelCollectionResponse> responseEntity = restTemplate.exchange(uriComponents.toUriString(), HttpMethod.GET, requestEntity, GetParcelCollectionResponse.class);
    return responseEntity.getBody();
  }
}
