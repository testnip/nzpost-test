@automation
Feature: Clear Search Input

  Background: User is on store locator page
    Given User is on store locator page

  Scenario: Clearing search input from the store locator after entering the search value
    Given User has address "2014"
    And User enters store search
    When User selects the clear search icon
    Then Entered address should be cleared
    And The suggestion list should disappear

  Scenario: Clearing search input from the store locator after performing search on the search value
    Given User has address "2014"
    And User performs store search
    When User selects the clear search icon
    Then Entered address should be cleared
    And The result list should be retained
