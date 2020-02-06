@wip
Feature: Address Search Map

  Background: User is on address search page
    Given User is on address search page

  Scenario Outline: Valid map for postcode or address "<address>" using enter
    Given User has address "<address>"
    When User performs address search
    Then User should see the correct map for "<address>"

    Examples:
      | address                                  |
      | 2104                                     |

  Scenario Outline: Valid map for postcode or address "<address>" using satellite view
    Given User has address "<address>"
    When User performs address search
    Then User click on map button
    And User should see the correct map for "<address>"

    Examples:
      | address |
      | 2014    |

  Scenario Outline: Valid map for postcode or address "<address>"using satellite view without label
    Given User has address "<address>"
    When User performs address search
    Then User click on map button
    And User click on label button
    And User should see the correct map for "<address>"

    Examples:
      | address |
      | 2013    |
