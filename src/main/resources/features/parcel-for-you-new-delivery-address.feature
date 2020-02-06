@automation
Feature: Parcel for you-New delivery address

  Background: User has selected redeliver to new address option for parcel
    Given User has tracking number "8963063677002502AKL004AS"
    And User is on new delivery address page

  Scenario Outline: New delivery address with partial address "<partial address>" - select "<address>" from suggestion list
    And User has address "<partial address>"
    And User selects "<address>" from available addresses suggestion list
    When User submit new address using button
    Then User should be shown contact details page

    Examples:
      | partial address | address                                        |
      | PO Box 201227   | PO Box 201227, Auckland Airport, Auckland 2150 |

  Scenario Outline:New delivery address - Enter Invalid address "<address>"
    And User enters address "<address>"
    When User submit address
    Then User should be shown a message "<message>"

    Examples:
      | address | message                                                                                                            |
      | ABCD    | Sorry, that address is invalid. Please begin typing the new delivery address and select from the options provided. |
      |         | Sorry, that address is invalid. Please begin typing the new delivery address and select from the options provided. |

  Scenario Outline: New delivery address - Invalid address "<address>"
    And User enters address "<address>"
    When User submit new address using button
    Then User should be shown a message "<message>"

    Examples:
      | address | message                                                                                                            |
      | ABCD    | Sorry, that address is invalid. Please begin typing the new delivery address and select from the options provided. |
      |         | Sorry, that address is invalid. Please begin typing the new delivery address and select from the options provided. |

  Scenario Outline: New delivery address - Entering an address outside 75km of original delivery address "<address>"
    And User has address "<address>"
    And User selects "<address>" from available addresses suggestion list
    When User submit new address using button
    Then User should be shown a message "<message>"

    Examples:
      | address                                      | message                                                                                    |
      | 25 Dawson Access Road, Kaitaia, Kaitaia 0482 | Sorry, this address is not within 75km of the original delivery address. Please try again. |

  Scenario: New delivery address - Previous button
    When User submit previous
    Then User should be shown redelivery option page

  Scenario Outline: New delivery address - Entering same original and new address "<address>"
    And User has address "<address>"
    And User selects "<address>" from available addresses suggestion list
    When User submit new address using button
    Then User should be shown a message "<message>"

    Examples:
      | address                                          | message                                                                                                                                                                                                                       |
      | 14 Iwinuku Crescent, Wattle Downs, Auckland 2103 | Please provide an address which is different from the original delivery address. If you would like to have your parcel redelivered to the same address, please go back and select the ‘Redeliver to the same address’ option. |