@automation
Feature: Parcel for you - Redeliver to same address - DateTime with redelivery instructions

  Background: User has submitted contact details
    Given User has tracking number "8963063677002502AKL004AS"
    And User is on date time page


  Scenario: Datetime with redelivery instructions - Date time picker
    And User select date and time
    When User submit date and time
    Then User should see redelivery instructions page


  Scenario Outline: Datetime - No date and time selected
    When User submit date and time
    Then User should be shown a message "<message>"

    Examples:
      | message                          |
      | Please select a redelivery date. |

  Scenario: Redeliver to same address - Date time previous button
    When User submit previous
    Then User should be shown contact details page
