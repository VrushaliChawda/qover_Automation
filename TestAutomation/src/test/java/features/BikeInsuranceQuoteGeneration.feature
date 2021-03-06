Feature: Generate bike insurance quote

  Background:
    Given User navigates to the "https://appqoverme-ui.sbx.qover.io/bike/quote?key=pk_9153C6B0DDB3C97367AE&locale=en-BE" page

  Scenario: mandatory field validation
    When User clicked on see price
    Then Error message is displayed for "BikeType"
    And  Error message is displayed for "BikePrice"

  Scenario: validate minimum bike price is 250
    When User enter bike price "150"
    Then Error message is displayed for "MinValue"

  Scenario: validate maximum bike price is 10000
    When User enter bike price "12000"
    Then Error message is displayed for "MaxValue"

  Scenario: Generating Quote with valid price
    When User adds bike details
    | BikeType | BikePrice | GPS Tracker |
    | Racing bike | 2390   | Equipped with GPS tracker |

    Then User clicked on see price
    And Quote is generated with correct price
    | Theft | Omnium |
    | €123.17/year | €151.44/year |
    | €10.27/month | €12.62/month |
