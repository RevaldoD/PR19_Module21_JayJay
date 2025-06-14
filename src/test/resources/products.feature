@product
Feature: Products

  @valid-addtocart
  Scenario: Add product to cart
    Given the user is on the Products page
    When the user clicks the Add to cart button for a product
    Then the product should appear in the cart
