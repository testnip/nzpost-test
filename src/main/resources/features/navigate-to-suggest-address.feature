 @automation
Feature: User navigates to suggest an address page

  Background: User is on address search page
    Given User is on address search page

  Scenario Outline: Navigate to suggest an address page after searching for address "<address>"
    Given User has address "<address>"
    When User performs address search
    And User click on suggest an address button
    Then User should see suggest an address page

    Examples:
      | address                                   |
      | 7 wai                                     |
      | 191 Dunkirk Road, Panmure, Auckland, 1072 |
