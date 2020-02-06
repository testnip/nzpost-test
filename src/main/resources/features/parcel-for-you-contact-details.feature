@automation
Feature: Parcel For You - Redeliver to same address - Contact details

  Background: User has entered all the details for parcel
    Given User has tracking number "8963063677002502AKL004AS"
    And User is on contact details page

  Scenario Outline: Contact details - Valid contact details
    Given User has contact details "<contact details>"
    When User submits the contact details form
    Then User should see date and time page

    Examples:
      | contact details  |
      | valid-company    |
      | valid-no-company |

  Scenario Outline: Contact details - Invalid first name details for contact details
    Given User has contact details "<contact details>"
    When User enters details on contact details form
    Then User should see first name validation error

    Examples:
      | contact details    |
      | invalid-first-name |

  Scenario Outline: Contact details - Invalid last name details for contact details
    Given User has contact details "<contact details>"
    When User enters details on contact details form
    Then User should see last name validation error

    Examples:
      | contact details   |
      | invalid-last-name |

  Scenario Outline: Contact details - Invalid email details for contact details
    Given User has contact details "<contact details>"
    When User enters details on contact details form
    Then User should see email detail validation error

    Examples:
      | contact details |
      | invalid-email   |

  Scenario Outline: Contact details - Invalid phone details for contact details
    Given User has contact details "<contact details>"
    When User enters details on contact details form
    Then User should see phone number validation error

    Examples:
      | contact details      |
      | invalid-phone-number |

  Scenario Outline: Contact details - Missing details for contact details
    Given User has contact details "<contact details>"
    When User enters details on contact details form
    Then User should see required details errors

    Examples:
      | contact details         |
      | invalid-contact-details |

  Scenario: Redelivery option - Contact details previous button
    When User submit previous
    Then User should be shown redelivery option page