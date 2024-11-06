
@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @ErrorValidate
  Scenario Outline: Title of your scenario outline
    Given Landed on Ecommerce Page
		When Logged in with Username <name> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name  							| password        |
      | ankam7063@gmail.com | Seleniumudmy@7  |