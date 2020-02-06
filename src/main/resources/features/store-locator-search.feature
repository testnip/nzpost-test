@automation
Feature: Search store locations

  Background: User is on store locator page
    Given User is on store locator page

  Scenario Outline: Display a list of available store locations when searching by address "<address>"
    Given User has address "<address>"
    When User enters store search
    Then User should be shown all available store location suggestions for the given address

    Examples:
      | address      |
      | 100 Queen St |
      #| 1010         |
      | Onehunga     |
      #| Otorohanga   |
      | Wellington   |

  Scenario Outline: Store location search with partial address "<partial address>" - select "<address>" from suggestion list
    Given User has address "<partial address>"
    When User selects "<address>" from suggestion list
    Then User should be shown all available store locations

    Examples:
      | partial address | address                 |
      | Pt E            | Point England, Auckland |
      | Wa              | Waitangirua Laundromat  |
      | 12              | Greytown Postshop       |

  Scenario Outline: Store location search with partial address "<partial address>"
    Given User has address "<partial address>"
    When User performs store search
    Then User should be shown all available store locations

    Examples:
      | partial address |
      | Pt E            |
      | Wa              |
      | 12              |


  Scenario Outline: Store location search with invalid address "<invalid address>"
    Given User has address "<invalid address>"
    When User performs store search
    Then User should be shown a message "<invalid location message>" with title "<invalid location title>"

    Examples:
      | invalid address | invalid location message                                                                     | invalid location title                 |
      | auckland1       | Please check the address you’ve entered and try again - avoiding characters such as !#$%^&@. | We can't find a match for your search. |
      | auck@           | Please check the address you’ve entered and try again - avoiding characters such as !#$%^&@. | We can't find a match for your search. |
      | auckl-          | Please check the address you’ve entered and try again - avoiding characters such as !#$%^&@. | We can't find a match for your search. |