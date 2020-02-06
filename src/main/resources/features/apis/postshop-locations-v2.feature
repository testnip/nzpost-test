@automation @api
Feature: Postshop location apis version2
  <List of postshop locations and address suggestions for valid address using version2>

  Scenario Outline: Get a list of postshop locations with map extent coordinates for address <address>
    Given I have a map coordinates for address "<address>"
    When I call postshop locations coordinates endpoint to get a list of postshop locations
    Then I should get a list of postshop locations for that coordinates address "<address>"

    Examples:

      | address  |
      | auckland |


  Scenario Outline: Get a list of postshop locations with keyword for address "<address>"
    Given I have a keyword for address "<address>"
    When I call postshop locations coordinates endpoint to get a list of postshop locations
    Then I should get a list of postshop locations for that coordinates address "<address>"

    Examples:

      | address     |
      | wellington  |
      | auckland1   |
      | @auckland   |
      | w_ellington |
      | -dunedin1   |


  Scenario Outline: Get a list of postshop locations with geolocation enabled for address "<address>"
    Given I have a geolocation coordinates for address "<address>"
    When I call postshop locations coordinates endpoint to get a list of postshop locations
    Then I should get a list of postshop locations for that coordinates address "<address>"

    Examples:

      | address              |
      | Highbrook-EastTamaki |

  Scenario Outline: Get a list of suggested address with a partial address "<partial address>"
    Given I have an address "<partial address>"
    When I call suggestions endpoint to get a list of suggested address
    Then I should get a list of suggested address for that address

    Examples:

      | partial address |
      | auck            |