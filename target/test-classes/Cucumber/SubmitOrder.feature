
@tag
Feature: Purchase the Order from ECommerce Website
  I want to use this template for my feature file


Background:
Given Landed on Ecommerce Page

  @E2E
  Scenario Outline: Positive test of Submitting the Order
    Given Logged in with Username <name> and password <password>
    When I add the product <productName> to Cart
    And Checkout <productName> and submit the Order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name  							| password        | productName  |
      | ankam7063@gmail.com | Seleniumudemy@7 | ZARA COAT 3  |

