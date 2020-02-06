# Won't include this in the test automation suite since the design of overlapped icons has not been finalised yet.
@wip
Feature: Search store locations on map

  Background: User is on store locator page
    Given User is on store locator page

  Scenario Outline: Store location search with partial address "<address>"
    Given User has address "<address>"
    When User performs store search
    Then User should see correct store locations on the map

    Examples:
      | address  |
      | Pt E     |
      | Auckland |
