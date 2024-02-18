Feature: The greeting API behaves as expected

  Background:
    Given the greeting API has been reset

  Scenario: The greeting API return a default greeting
    Given I have a greeting API client
    When I fetch the current greeting
    Then I get a response of "hello world"

  Scenario: When I update the greeting the API returns the new greeting
    Given I have a greeting API client
    When I set the interjection to "Hi"
    Then I get a response of "Hi world"
    When I set the noun to "Adastral"
    Then I get a response of "Hi Adastral"
    When I fetch the current greeting
    Then I get a response of "Hi Adastral"
