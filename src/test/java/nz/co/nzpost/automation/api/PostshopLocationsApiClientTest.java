package nz.co.nzpost.automation.api;

import nz.co.nzpost.automation.api.responses.GetPostshopLocationsResponse;
import nz.co.nzpost.automation.domain.Address;
import nz.co.nzpost.automation.domain.PostshopLocation;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static shiver.me.timbers.data.random.RandomStrings.someString;

public class PostshopLocationsApiClientTest {

  private PostshopLocationsApiClient postshopLocationsApiClient;
  private RestTemplate restTemplate;
  private final String hostApi = someString();
  private ApiProperties apiProperties;

  @Before
  public void setUp() throws Exception {
    restTemplate = mock(RestTemplate.class);
    apiProperties = mock(ApiProperties.class);
    postshopLocationsApiClient = new PostshopLocationsApiClient(restTemplate, apiProperties);
  }

  @Test
  @Ignore
  public void get() {
    Address address = new Address("1024");
    Map<String, String> uriVariables = new HashMap<>();
    uriVariables.put("query", address.getValue());

    GetPostshopLocationsResponse expectedGetPostshopLocationsResponse = mock(GetPostshopLocationsResponse.class);

    // given
    given(restTemplate.getForObject(format("%s%s", hostApi, "/v1/locations"), GetPostshopLocationsResponse.class, uriVariables)).willReturn(expectedGetPostshopLocationsResponse);

    // when
    GetPostshopLocationsResponse<List<PostshopLocation>> actualGetPostshopLocationsResponse = postshopLocationsApiClient.get(address);

    // then
    then(restTemplate).should().getForObject(format("%s%s", hostApi, "/v1/locations"), GetPostshopLocationsResponse.class, uriVariables);
    assertThat(actualGetPostshopLocationsResponse).isEqualTo(expectedGetPostshopLocationsResponse);
  }

}