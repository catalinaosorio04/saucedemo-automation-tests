@cart
Feature: Shopping cart functionality
  As a logged in user
  I want to manage items in my shopping cart
  So that I can control what I want to purchase

  Background:
    Given I am logged in as "standard_user" with password "secret_sauce"
    And I am on the products page

  @smoke @positive
  Scenario: Add single product to cart
    When I add "Sauce Labs Backpack" to the cart
    Then the cart badge should show "1"
    And I go to the cart page
    Then I should see "Sauce Labs Backpack" in the cart

  @positive
  Scenario: Add multiple products to cart
    When I add the following products to cart:
      | Sauce Labs Backpack    |
      | Sauce Labs Bike Light  |
      | Sauce Labs Bolt T-Shirt|
    Then the cart badge should show "3"
    And I go to the cart page
    Then I should see all added products in the cart


