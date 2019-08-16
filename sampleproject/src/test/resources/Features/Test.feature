Feature: Basic Validation

  Scenario: Basic Test Validation
    Given I configure Request-ContentType "application/json"
    And I invoke request type "GET" with resource path "/users"
    Then I receive status code 200

  Scenario: Multi validation test
    Given I configure Request-ContentType "application/json"
    And I invoke request type "GET" with resource path "/users?page=2"
    Then I receive status code 200
