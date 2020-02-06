
@automation
Feature: Map interaction with store result list

  Background: User is on store locator page
    Given User is on store locator page

  Scenario Outline: Map interaction with valid address "<address>" - select icon for "<location>"
    Given User has address "<address>"
    And User performs store search
    When User select "<location>" with location id "<location id>" displayed on the map
    Then User should see "<location>" highlighted in the result list

    Examples:
      | address    | location          | location id |
      | Auckland   | Kiwibank ATM      | 155899      |
      | Wellington | Hataitai Pharmacy | 123056      |