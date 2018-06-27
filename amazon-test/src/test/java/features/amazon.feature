Feature: AmazonTest

  Scenario: Search Amazon website for Nikon D3X
    Given user goes to "amazon.com"
    When user searches for Nikon product
    And user sorts results from highest price to lowest
    And user selects second product from top
    And user clicks it for details
    Then product containing Nikon D3X is found
