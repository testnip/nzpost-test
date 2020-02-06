@automation
Feature: Address Search Errors

  Background: User is on address search page
    Given User is on address search page

  Scenario Outline: Display a message when Address and Postcode Finder times out
    Given User has address "<address>"
    When User performs address search
    Then User should see icon "<icon type>" and message "<address search message>"

    Examples:
      | address               | icon type | address search message                                   |
      | High Street, Auckland | INVALID   | Looks like something's gone wrong.Please try again later. |

  Scenario Outline: Display a message when Address and Postcode Finder times out on search icon
    Given User has address "<address>"
    When User performs address search by search icon
    Then User should see icon "<icon type>" and message "<address search message>"

    Examples:
      | address               | icon type | address search message                                   |
      | High Street, Auckland | INVALID   | Looks like something's gone wrong.Please try again later. |


