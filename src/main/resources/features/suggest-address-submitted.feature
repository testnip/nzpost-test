@automation
Feature: Submit suggest address

  Background: User is on suggest an address page
    Given User is on suggest an address page

  Scenario Outline: Back to address post code finder page
    Given User has address suggestion "<address suggestion>"
    And User submits the address suggest form
    When User select back to address postcode finder button
    Then User should see address postcode finder page

    Examples:
      | address suggestion  |
      | valid-myaddress     |
