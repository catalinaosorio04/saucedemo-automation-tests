@e2e @regression
Feature: End to end shopping flow
  As a customer
  I want to complete a full shopping experience
  So that I can purchase products successfully

  @smoke
  Scenario: Complete purchase flow
    Given I am on the SauceDemo login page
    When I login with username "standard_user" and password "secret_sauce"
    And I add "Sauce Labs Backpack" to the cart
    And I add "Sauce Labs Bike Light" to the cart
    And I go to the cart page

