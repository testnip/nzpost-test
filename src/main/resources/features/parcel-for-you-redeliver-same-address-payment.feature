 @automation
Feature: Parcel for you - Redeliver to same address - Redelivery instructions - Payment Page

  Background: User has reviewed summary page
    Given User has tracking number "8963063677002502AKL004AS"
    And User is on payment page


  Scenario Outline: Payment Successful
    Given User has payment details "<payment details>"
    When User submits payment form
    Then User should see success page

    Examples:
      | payment details  |
      | valid-details    |
      | valid-no-company |

  Scenario Outline: Card name not entered
    Given User has payment details "<payment details>"
    When User enters details on payment form
    Then User should see credit card name error

    Examples:
      | payment details |
      | card-no-name    |

  Scenario Outline: Invalid card number
    Given User has payment details "<payment details>"
    When User enters details on payment form
    Then User should see credit card number validation error

    Examples:
      | payment details     |
      | invalid-card-number |


  Scenario Outline: Expiry date is not valid
    Given User has payment details "<payment details>"
    When User enters details on payment form
    Then User should see credit card expiry date validation error

    Examples:
      | payment details     |
      | invalid-card-expiry |


  Scenario Outline: Credit Card security code is not valid
    Given User has payment details "<payment details>"
    When User enters details on payment form
    Then User should see security code validation error

    Examples:
      | payment details       |
      | invalid-card-security |


  Scenario Outline: Credit Card response times out
    Given User has payment details "<payment details>"
    And User enters details on payment form
    When User submit details
    Then User should see timeout error

    Examples:
      | payment details    |
      | valid-card-timeout |

  Scenario Outline: Credit Card is declined
    Given User has payment details "<payment details>"
    And User enters details on payment form
    When User submit details
    Then User should see declined error

    Examples:
      | payment details     |
      | valid-card-declined |

  Scenario Outline: Invalid first name in billing details
    Given User has payment details "<payment details>"
    When User enters details on payment form
    Then User should see first name validation error

    Examples:
      | payment details            |
      | invalid-billing-first-name |

  Scenario Outline: Invalid last name in billing details
    Given User has payment details "<payment details>"
    When User enters details on payment form
    Then User should see last name validation error

    Examples:
      | payment details           |
      | invalid-billing-last-name |

  Scenario Outline: Invalid street name in billing details
    Given User has payment details "<payment details>"
    When User enters details on payment form
    Then User should see invalid street validation error

    Examples:
      | payment details         |
      | invalid-billing-address |

  Scenario Outline: Invalid suburb name in billing details
    Given User has payment details "<payment details>"
    When User enters details on payment form
    Then User should see suburb validation error

    Examples:
      | payment details        |
      | invalid-billing-suburb |

  Scenario Outline: Invalid city name in billing details
    Given User has payment details "<payment details>"
    When User enters details on payment form
    Then User should see invalid city validation error

    Examples:
      | payment details      |
      | invalid-billing-city |

  Scenario Outline: Invalid postcode in billing details
    Given User has payment details "<payment details>"
    When User enters details on payment form
    Then User should see postcode validation error

    Examples:
      | payment details          |
      | invalid-billing-postcode |

  Scenario Outline: Invalid country in billing details
    Given User has payment details "<payment details>"
    When User enters details on payment form
    Then User should see country validation error

    Examples:
      | payment details         |
      | invalid-billing-country |

  Scenario Outline: Billing details same as delivery address
    Given User has payment details "<payment details>"
    When User submit card details
    Then User should see success page

    Examples:
      | payment details          |
      | only-credit-card-details |

  Scenario Outline: Payment details - Missing payment details
    Given User has payment details "<payment details>"
    When User enters details on payment form
    Then User should see required errors

    Examples:
      | payment details         |
      | payment-missing-details |

  Scenario: Payment details - Missing payment details - Click on Pay
    When User submit details
    Then User should see required errors

