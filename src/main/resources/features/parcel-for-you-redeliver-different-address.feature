@automation
Feature: Parcel For You - Redeliver to different address

  Background: User has selected redelivery to different address
    Given User has tracking number "8963063677002502AKL004AS"

  Scenario: Redeliver to different address - Previous button
    And User is on contact page
    When User submit previous
    Then User should be shown new delivery address option

  Scenario Outline: Redeliver to different address - Redelivery Instructions - "<leave parcel>"
    Given User is on authorised page
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

  Scenario: Redeliver to different address - Summary Page with atl
    Given User is on summary review page
    When User view information
    Then User should be shown redeliver different address information
    And User should be shown different summary cards

  Scenario: Edit redeliver to different address - Redelivery Instructions - Summary page
    And User is on summary review page
    When User click on edit on different address
    Then User should be shown new delivery address option

  #Scenario: Redeliver to different address - Success page
   # Given User is on summary review page without atl
   # When User view information
   # Then User should be shown redeliver different address information
    #And User should be shown different cards

  Scenario: Summary page - Different address redelivery instructions - Submit page
    And User is on summary review page
    And User check terms and conditions
    When User submit summary
    Then User should see success page for different address