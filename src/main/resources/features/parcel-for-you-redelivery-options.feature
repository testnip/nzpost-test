@automation
Feature: Parcel for you-Redelivery Options

  Background: User has submitted original address for parcel

    Given User has tracking number "8963063677002502AKL004AS"
    And User is on redelivery options page

  Scenario: Redelivery options - Redelivery to same address
    And User has selected redeliver to same address
    When User submit option
    Then User should be shown contact details page

  Scenario: Redelivery Options - Redelivery to different address
    And User has selected redeliver to different address
    When User submit option
    Then User should be shown new delivery address option

  Scenario: Redelivery options - Redeliver to collection point
    And User has selected redeliver to collection point
    When User submit option
    Then User should be shown parcel collect redelivery

  Scenario: Redelivery options - Previous button
    When User submit previous
    Then User should be shown original delivery address