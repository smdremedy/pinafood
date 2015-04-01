Feature: Login feature

  Scenario: As a valid user I can log into my app
    When take picture
    When I press "Login"
    Then I see "Welcome to coolest app ever"
    Then take picture
