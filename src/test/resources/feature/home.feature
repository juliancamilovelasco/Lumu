Feature: Word Counter functionality on wordcounter.net

  Scenario: Validate word count
    Given I navigate to the WordCounter website
    When I input the text "Hello, this is a test message."
    Then I should see the word count as 6

  Scenario: Validate character count
    Given I navigate to the WordCounter website
    When I input the text "Hello, this is a test message."
    Then I should see the character count as 30

  Scenario: Validate keyword density (most repeated words)
    Given I navigate to the WordCounter website
    When I input the text "apple apple orange banana apple banana"
    Then I should see the keyword density as "apple: 3, banana: 2, orange: 1"