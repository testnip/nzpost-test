
@geolocation
Feature: Quick search store locations in major cities

  Background: User is on store locator page
    Given User is on store locator page

  Scenario Outline: Display a list of available store locations when selecting city "<city>"
    Given User has address "<city>"
    When User selects city from quick search
    Then User should be shown all available store locations

    Examples:
      | city         |
      | Auckland     |
      | Wellington   |
      | Christchurch |
      | Dunedin      |
