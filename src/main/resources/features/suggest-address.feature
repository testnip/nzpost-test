@automation
Feature: Suggest An Address

  Background: User is on suggest an address page
    Given User is on suggest an address page

  Scenario Outline: Valid details for suggest an address
    Given User has address suggestion "<address suggestion>"
    When User submits the address suggest form
    Then User should see the success page for the suggested address
    And User should see back to address and postcode finder page button

    Examples:
      | address suggestion  |
      | valid-myaddress     |
      | valid-not-myaddress |

  Scenario Outline: Invalid full name details for suggest an address
    Given User has address suggestion "<address suggestion>"
    When User enters address on address suggest form
    Then User should see full name validation error
    And User should see submit address button disabled

    Examples:
      | address suggestion   |
      | invalid-name-address |

  Scenario Outline: Invalid email details for suggest an address
    Given User has address suggestion "<address suggestion>"
    When User enters address on address suggest form
    Then User should see email validation error
    And User should see submit address button disabled

    Examples:
      | address suggestion    |
      | invalid-email-address |

  Scenario Outline: Invalid street details for suggest an address
    Given User has address suggestion "<address suggestion>"
    When User enters address on address suggest form
    Then User should see street validation error
    And User should see submit address button disabled

    Examples:
      | address suggestion     |
      | invalid-street-address |

  Scenario Outline: Invalid suburb details for suggest an address
    Given User has address suggestion "<address suggestion>"
    When User enters address on address suggest form
    Then User should see suburb validation error
    And User should see submit address button disabled

    Examples:
      | address suggestion     |
      | invalid-suburb-address |

  Scenario Outline: Invalid city details for suggest an address
    Given User has address suggestion "<address suggestion>"
    When User enters address on address suggest form
    Then User should see city validation error
    And User should see submit address button disabled

    Examples:
      | address suggestion   |
      | invalid-city-address |

  Scenario Outline: Missing details for suggest an address
    Given User has address suggestion "<address suggestion>"
    When User enters address on address suggest form
    Then User should see required field errors
    And User should see submit address button disabled

    Examples:
      | address suggestion      |
      | invalid-missing-address |

