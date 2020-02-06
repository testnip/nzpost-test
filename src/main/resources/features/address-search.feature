@automation
Feature: Address Search

  Background: User is on address search page
    Given User is on address search page

  Scenario Outline: No postcode or address using enter
    Given User has address "<address>"
    When User performs address search
    Then User should see icon "<icon type>" and message "<address search message>"

    Examples:
      | address | icon type | address search message   |
      |         | INVALID   | Please enter an address. |

  Scenario Outline: No postcode or address using search
    Given User has address "<address>"
    When User performs address search by search icon
    Then User should see icon "<icon type>" and message "<address search message>"

    Examples:
      | address | icon type | address search message   |
      |         | INVALID   | Please enter an address. |


  Scenario Outline: Valid postcode "<postcode>"
    Given User has address "<postcode>"
    When User performs address search
    Then User should see "<postcode>" and "<city>" in the address result
    And User should see icon "<icon type>" and message "<address search message>"
    And User should see additional information for valid postcode "valid-postcode-message"
    Examples:
      | postcode | city      | icon type | address search message    |
      | 2014     | Auckland  | VALID     | This is a valid postcode. |
      | 7007     | Murchison | VALID     | This is a valid postcode. |

  Scenario Outline: Valid postcode "<postcode>"
    Given User has address "<postcode>" with preceding spaces
    When User performs address search
    Then User should see "<postcode>" and "<city>" in the address result
    And User should see icon "<icon type>" and message "<address search message>"
    And User should see additional information for valid postcode "valid-postcode-message"
    Examples:
      | postcode | city     | icon type | address search message    |
      | 2014     | Auckland | VALID     | This is a valid postcode. |

  Scenario Outline: Invalid address or postcode "<address>"
    Given User has address "<address>"
    When User performs address search
    And User should see icon "<icon type>" and message "<address search message>"
    And User should see additional information for valid postcode "invalid-postcode-message"
    Examples:
      | address                              | icon type | address search message                  |
      | 1013                                 | INVALID   | This isn't a complete address.          |
      | PO Box                               | INVALID   | This isn't a complete address.          |
      | Private Bag                          | INVALID   | This isn't a complete address.          |
      | West Coast Rd, RD 14, WHANGANUI 4584 | INVALID   | This isn't a complete address.          |
      | 3/                                   | INVALID   | This isn't a complete address.          |
      | PO Box 80000000                      | INVALID   | We can't find this address or postcode. |
      | Flat 12345 Albans Road               | INVALID   | We can't find this address or postcode. |


  Scenario Outline: Special characters "<address>"
    Given User has address "<address>"
    When User starts address search with special characters
    And User should see icon "<icon type>" and message "<address search message>"
    And search icon should be disabled
    Examples:
      | address | icon type | address search message                                 |
      | @       | INVALID   | Please try again, avoiding characters such as !#$%^&@. |
      | #@      | INVALID   | Please try again, avoiding characters such as !#$%^&@. |
      | 2014@   | INVALID   | Please try again, avoiding characters such as !#$%^&@. |

  Scenario Outline: Partial postcode "<partial postcode>" - select "<postcode>" from primary postcode list
    Given User has address "<partial postcode>"
    When User selects "<postcode>" from primary available postcodes
    Then User should see "<postcode>" and "<city>" in the address result
    And User should see icon "<icon type>" and message "<address search message>"
    And User should see additional information for valid postcode "valid-postcode-message"
    Examples:
      | partial postcode | postcode | city      | icon type | address search message    |
      | 7                | 7007     | Murchison | VALID     | This is a valid postcode. |

  Scenario Outline: Partial address "<partial address>" - select "<address>" from primary address list
    Given User has address "<partial address>"
    When User selects "<address>" from primary available addresses
    Then User should see "<postcode>" and "<address>" in the address result
    And User should see icon "<icon type>" and message "<address search message>"
    And User should see days standard mail delivery days "<days>"
    Examples:
      | partial address | postcode | address                                           | icon type | address search message        | days          |
      | 7 Wai           | 2103     | 7 Wai Iti Place, Clendon Park, Auckland 2103      | VALID     | You can post to this address. | Tue, Thu, Sat |
      | 3/              | 1010     | 3/1 Cross Street, Auckland Central, Auckland 1010 | VALID     | You can post to this address. | Tue, Thu, Sat |

  Scenario Outline: Partial po box "<partial address>" - select "<address>" from primary address list
    Given User has address "<partial address>"
    When User selects "<address>" from primary available addresses
    Then User should see "<postcode>" and "<address>" in the address result
    And User should see icon "<icon type>" and message "<address search message>"
    Examples:
      | partial address | postcode | address                  | icon type | address search message        |
      | PO Box 1        | 0150     | PO Box 1, Hikurangi 0150 | VALID     | You can post to this address. |

  Scenario Outline: Partial private bag "<partial private bag>" - select "<private bag>" from primary address list
    Given User has address "<partial private bag>"
    When User selects "<private bag>" from primary available addresses
    Then User should see "<postcode>" and "<private bag>" in the address result
    And User should see icon "<icon type>" and message "<address search message>"
    And User should see copy this address button
    Examples:
      | partial private bag | postcode | private bag               | icon type | address search message        |
      | Private Bag 4       | 4350     | Private Bag 4, Mokau 4350 | VALID     | You can post to this address. |

  Scenario Outline: Partial postcode "<partial postcode>" - select "<postcode>" from secondary postcode list
    Given User has address "<partial postcode>"
    When User selects "<postcode>" from secondary available postcodes
    Then User should see "<postcode>" and "<city>" in the address result
    And User should see icon "<icon type>" and message "<address search message>"
    And User should see additional information for valid postcode "valid-postcode-message"
    Examples:
      | partial postcode | postcode | city      | icon type | address search message    |
      | 7                | 7007     | Murchison | VALID     | This is a valid postcode. |

  Scenario Outline: Valid city "<city>"
    Given User has address "<city>"
    When User performs address search by search icon
    And User should see icon "<icon type>" and message "<address search message>"
    And User should see additional information for valid postcode "valid-city-message"
    Examples:
      | city     | icon type | address search message                                                           |
      | Auckland | INFO      | This isn’t a complete address. There's more than one postcode for this location. |

  Scenario Outline: Valid suburb "<suburb>"
    Given User has address "<suburb>"
    When User performs address search by search icon
    And User should see icon "<icon type>" and message "<address search message>"
    And User should see additional information for valid postcode "invalid-postcode-message"
    Examples:
      | suburb             | icon type | address search message         |
      | Ponsonby           | INVALID   | This isn't a complete address. |
      | Auckland, Ponsonby | INVALID   | This isn't a complete address. |

  Scenario Outline: Valid address "<address>"
    Given User has address "<address>"
    When User performs address search
    Then User should see "<postcode>" and "<address>" in the address result
    And User should see icon "<icon type>" and message "<address search message>"
    And User should see days standard mail delivery days "<days>"
    And User should see copy this address button
    Examples:
      | address                                  | postcode | icon type | address search message        | days          |
      | 16 Cornwall Place, Karaka, Papakura 2113 | 2113     | VALID     | You can post to this address. | Mon, Wed, Fri |

  Scenario Outline: Valid rural address "<address>"
    Given User has address "<address>"
    When User performs address search
    Then User should see "<postcode>" and "<address>" in the address result
    And User should see icon "<icon type>" and message "<address search message>"
    And User should see days standard mail delivery days "<days>"
    And User should see copy this address button
    Examples:
      | address                                   | postcode | icon type | address search message        | days                    |
      | 7007 South Road, RD 37, New Plymouth 4381 | 4381     | VALID     | You can post to this address. | Mon, Tue, Wed, Thu, Fri |

  Scenario Outline: Valid apartment address "<address>"
    Given User has address "<address>"
    When User performs address search
    Then User should see "<postcode>" and "<address>" in the address result
    And User should see icon "<icon type>" and message "<address search message>"
    And User should see days standard mail delivery days "<days>"
    And User should see copy this address button
    Examples:
      | address                                    | postcode | icon type | address search message        | days          |
      | 3/454 Church Street, Palmerston North 4410 | 4410     | VALID     | You can post to this address. | Mon, Wed, Fri |

  Scenario Outline: Valid private bag "<address>"
    Given User has address "<address>"
    When User performs address search
    Then User should see "<postcode>" and "<address>" in the address result
    And User should see icon "<icon type>" and message "<address search message>"
    And User should see copy this address button
    Examples:
      | address                         | postcode | icon type | address search message        |
      | Private Bag 4072, Matamata 3440 | 3440     | VALID     | You can post to this address. |

  Scenario Outline: Not a postal address "<address>"
    Given User has address "<address>"
    When User performs address search
    And User should see icon "<icon type>" and message "<address search message>"
    And User should see additional information for valid postcode "not-postal-address-message"
    Examples:
      | address                                 | icon type | address search message                                                           |
      | State Highway 7, Dobson, Greymouth 7802 | INFO      | This isn’t a complete address. There's more than one postcode for this location. |

  Scenario Outline: Not a postal address "<address>"
    Given User has address "<address>"
    When User performs address search
    And User should see icon "<icon type>" and message "<address search message>"
    And User should see additional information for valid postcode "other-way-to-post-message"
    Examples:
      | address                              | icon type | address search message                               |
      | 7 Waana Street, Mourea, Rotorua 3074 | INFO      | This address doesn't receive Standard Mail delivery. |
      | Private Bag 4, Tuakau 2342           | INFO      | This address doesn't receive Standard Mail delivery. |

  Scenario Outline: PO Box address "<address>"
    Given User has address "<address>"
    When User performs address search
    And User should see icon "<icon type>" and message "<address search message>"
    And User should see copy this address button
    Examples:
      | address                   | icon type | address search message        |
      | PO Box 12, Hikurangi 0150 | VALID     | You can post to this address. |