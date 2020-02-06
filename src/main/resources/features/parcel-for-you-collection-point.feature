@automation
Feature: Parcel for you-Collection Point

  Background: User has submitted original address for parcel
    Given User has tracking number "8963063677002502AKL004AS"
    And  User is on parcel collection page

  Scenario: Collection point - Selecting a collection location from the list provided
    And User has selected parcel collection location
    When User submit location
    Then User should be shown contact details page

  Scenario Outline: Collection point - Display notification
    When User submit location
    Then User should be shown a message "<message>"

    Examples:
      | message                                                  |
      | Please select a collection point from the list provided. |

  Scenario: Collection Point - Previous button
    When User submit previous
    Then User should be shown redelivery option page