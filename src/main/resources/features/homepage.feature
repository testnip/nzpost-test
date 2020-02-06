@wip
Feature: User navigates to address search page

  Scenario: As a user I want to navigates to the address search page
    Given I have an address search url
    When I visit the url
    Then I should see the address search page
