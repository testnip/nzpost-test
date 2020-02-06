package nz.co.nzpost.automation.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "api")
public class ApiProperties {
  private String host;
  private String locationsUrl;
  private String suggestionsUrl;
  private String locationsUrlV2;
  private String suggestionsUrlV2;
  private String parcelAddressUrl;
  private String parcelCollectionUrl;


  public String getParcelCollectionUrl() {
    return parcelCollectionUrl;
  }

  public void setParcelCollectionUrl(String parcelCollectionUrl) {
    this.parcelCollectionUrl = parcelCollectionUrl;
  }

  public String getParcelAddressUrl() {
    return parcelAddressUrl;
  }

  public void setParcelAddressUrl(String parcelAddressUrl) {
    this.parcelAddressUrl = parcelAddressUrl;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getLocationsUrl() {
    return locationsUrl;
  }

  public void setLocationsUrl(String locationsUrl) {
    this.locationsUrl = locationsUrl;
  }

  public String getSuggestionsUrl() {
    return suggestionsUrl;
  }

  public void setSuggestionsUrl(String suggestionsUrl) {
    this.suggestionsUrl = suggestionsUrl;
  }

  public String getLocationsUrlV2() {
    return locationsUrlV2;
  }

  public void setLocationsUrlV2(String locationsUrlV2) {
    this.locationsUrlV2 = locationsUrlV2;
  }

  public String getSuggestionsUrlV2() {
    return suggestionsUrlV2;
  }

  public void setSuggestionsUrlV2(String suggestionsUrlV2) {
    this.suggestionsUrlV2 = suggestionsUrlV2;
  }
}
