@automation
Feature: Parcel for you

  Background: User is on parcel for you page
    Given User is on parcel for you page

  Scenario Outline: Using a Valid CTC or Tracking number "<tracking number>"
    Given User has tracking/CTC number "<tracking number>"
    When User submit tracking number
    Then User should see tracking number is eligible for redelivery

    Examples:
      | tracking number          |
      | 8963063677002502AKL004AS |
      | CTC26616953              |

  Scenario Outline: Using a Valid CTC or Tracking number "<tracking number>" - with button
    Given User has tracking/CTC number "<tracking number>"
    When User submit tracking number using button
    Then User should see tracking number is eligible for redelivery

    Examples:
      | tracking number          |
      | 8963063677002502AKL004AS |
      | CTC26616953              |

  Scenario Outline: Invalid CTC or tracking number "<tracking number>"
    Given User has tracking/CTC number "<tracking number>"
    When User submit tracking number
    Then User should see message "<message>"

    Examples:
      | tracking number | message                                                                                                           |
      | 123             | Sorry, you have supplied an invalid tracking number. Your tracking number should be between 9-24 characters long. |
      | 12345           | Sorry, you have supplied an invalid tracking number. Your tracking number should be between 9-24 characters long. |
      | CTC12345@       | Sorry, you have supplied an invalid tracking number. Your tracking number should be between 9-24 characters long. |
      | 12345678        | Sorry, you have supplied an invalid tracking number. Your tracking number should be between 9-24 characters long. |

  Scenario Outline: Using a tracking number "<tracking number>" that is not eligible for redelivery
    Given User has tracking/CTC number "<tracking number>"
    When User submit tracking number
    Then User should see a message "<message>" not available for redelivery

    Examples:
      | tracking number          | message                                                                                     |
      | 8963063449013903AKL004AS | Sorry, redelivery information for this parcel is not available yet. Please try again later. |
      | CTC123456789             | Sorry, redelivery information for this parcel is not available yet. Please try again later. |
