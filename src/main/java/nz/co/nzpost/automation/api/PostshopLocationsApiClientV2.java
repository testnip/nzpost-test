package nz.co.nzpost.automation.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nz.co.nzpost.automation.api.responses.GetPostshopLocationsResponse;
import nz.co.nzpost.automation.domain.PostshopLocation;
import nz.co.nzpost.automation.domain.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static java.lang.String.format;

@Component
public class PostshopLocationsApiClientV2 {

  private final RestTemplate restTemplate;
  private final ApiProperties apiProperties;
  private final ObjectMapper objectMapper;
  private final String scheme;
  private final String host;

  @Autowired
  public PostshopLocationsApiClientV2(RestTemplate restTemplate, ApiProperties apiProperties, ObjectMapper objectMapper) {
    this.restTemplate = restTemplate;
    this.apiProperties = apiProperties;
    this.objectMapper = objectMapper;


    String[] split = apiProperties.getHost().split("://");
    scheme = split[0];
    host = split[1];
  }

  public GetPostshopLocationsResponse<List<PostshopLocation>> get(Query query) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    httpHeaders.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
    httpHeaders.add("Authorization", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJuenBvc3Qtd2ViLXJlcGxhdGZvcm0tdGVzdCIsImlhdCI6MTUxNjIzOTAyMn0.-YJv6upFkpu_i5Ti70RSnqbWeNqeWPlx4yKgcnFhH6s");

    HttpEntity<Object> requestEntity = new HttpEntity<>(httpHeaders);

    try {
      StringBuilder urlBuilder = new StringBuilder();
      urlBuilder.append(format("%s%s?type={type}&value={value}", apiProperties.getHost(), apiProperties.getLocationsUrlV2()));
      if (query.getMax() != null) {
        urlBuilder.append("&max={max}");
      }

      ResponseEntity<GetPostshopLocationsResponse> responseEntity = restTemplate.exchange(urlBuilder.toString(), HttpMethod.GET, requestEntity, GetPostshopLocationsResponse.class, query.getType().toString(),query.getValue().getClass() == String.class ? query.getValue() : objectMapper.writeValueAsString(query.getValue()) , query.getMax());
      return responseEntity.getBody();
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException(format("Unable to get postshop locations with query %s", query));
    }
  }
}
