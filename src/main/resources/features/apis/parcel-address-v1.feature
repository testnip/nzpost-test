@automation @api
Feature: Parcel address apis V1

  Scenario Outline: Validate suggestion list for original delivery address "<address>"
    Given I have an address "<address>"
    When I call parcel address endpoint to validate suggestions
    Then I should be shown list of suggestions

    Examples:
      | address |
      | 14 iwi  |
