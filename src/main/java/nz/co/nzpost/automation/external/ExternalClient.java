package nz.co.nzpost.automation.external;

import cucumber.api.Scenario;

public interface ExternalClient {
  void updateSession(Scenario scenario);
}
