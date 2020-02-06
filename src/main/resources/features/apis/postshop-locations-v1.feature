@automation @api
Feature: Postshop location apis V1

  Scenario Outline: Get a list of postshop locations with a valid address "<valid address>"
    Given I have an address "<valid address>"
    When I call postshop locations endpoint to get a list of postshop locations
    Then I should get a list of postshop locations for that address

    Examples:
      | valid address |
      | christchurch  |


  Scenario Outline: Get a list of suggested address with a partial address "<partial address>"
    Given I have an address "<partial address>"
    When I call suggestions endpoint to get a list of suggested address
    Then I should get a list of suggested address for that address

    Examples:

      | partial address |
      | auck            |
