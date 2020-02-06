@automation
Feature: Parcel for you - Redeliver to same address - Authorised to leave

  Background: User has submitted date and time
    Given User has tracking number "8963063677002502AKL004AS"
    And User is on authorised to leave page


  Scenario Outline: Authorised to leave - Select leave my parcel options "<leave parcel>"
    And User select leave my parcel option "<leave parcel>"
    When User submit leave my parcel
    Then User should see summary page

    Examples:
      | leave parcel                    |
      | No, thanks                      |
      | Leave on the front porch / deck |
      | Leave on the back porch / deck  |
      | Leave by the front door         |
      | Leave by the back door          |
      | Leave in the mailbox            |

  Scenario Outline: Authorised to leave - Select leave my parcel - Other option
    And User select leave my parcel option "Other"
    And user enters instructions "<instructions>"
    When User submit leave my parcel
    Then User should see summary page

    Examples:
      | instructions        |
      |                     |
      | leave around garden |

  Scenario: Authorised to leave - back button
    When User submit previous
    Then User should see date and time page

  Scenario Outline: Authorised to leave - No leave my parcel option selected
    When User submit leave my parcel
    Then User should be shown a message "<message>"

    Examples:
      | message                  |
      | Please select an option. |