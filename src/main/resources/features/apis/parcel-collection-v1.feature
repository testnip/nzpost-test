@automation @api
Feature: Parcel collection apis V1

  Scenario Outline: Parcel collection address list "<address>"
    Given I have an address "<address>"
    When I call parcel collection endpoint to get parcel collection points
    Then I should be shown list of parcel collections

    Examples:
      | address |
      | 5045629 |
