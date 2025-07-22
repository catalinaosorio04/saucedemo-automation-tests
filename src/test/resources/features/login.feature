@login
Feature: Login functionality
  As a user
  I want to be able to login to the application
  So that I can access the products page

  Background:
    Given I am on the SauceDemo login page

  @smoke @positive
  Scenario: Successful login with valid credentials
    When I enter username "standard_user" and password "secret_sauce"
    And I click the login button
    Then I should be redirected to the products page
    And I should see the products inventory

  @negative
  Scenario: Login with invalid credentials
    When I enter username "invalid_user" and password "wrong_password"
    And I click the login button
    Then I should see an error message "Epic sadface: Username and password do not match any user in this service"

  @negative
  Scenario Outline: Login with missing credentials
    When I enter username "<username>" and password "<password>"
    And I click the login button
    Then I should see an error message "<error_message>"

    Examples:
      | username      | password     | error_message                           |
      |               | secret_sauce | Epic sadface: Username is required      |
      | standard_user |              | Epic sadface: Password is required      |
      |               |              | Epic sadface: Username is required      |

  @negative
  Scenario: Login with locked user
    When I enter username "locked_out_user" and password "secret_sauce"
    And I click the login button
    Then I should see an error message "Epic sadface: Sorry, this user has been locked out."