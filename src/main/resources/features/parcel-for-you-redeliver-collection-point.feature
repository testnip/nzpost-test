@automation
Feature: Parcel for you- Summary - Redeliver to collection point

  Background: User has submitted original address for parcel
    Given User has tracking number "8963063677002502AKL004AS"
    And  User is on summary review page with collection point address

  Scenario: Summary cards for redeliver to collection point
    When User view information
    Then User should be shown redeliver collection point
    And User should be shown different summary cards for parcel collection

  Scenario: Summary redeliver to collection point - Submit page
    And User check terms and conditions
    When User submit summary
    Then User should see success page for parcel collection

  Scenario: Summary collection point - Terms and condition
    When User click on redelivery terms and conditions
    Then User should be redirected to card to call link