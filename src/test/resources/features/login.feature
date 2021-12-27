Feature: Test login functions

  @login
  Scenario: Verify successful login
    Given user is in home page
    When user gives username and password as follows
      |john|demo|
    Then user is taken to "Accounts Overview" page

    @login
    Scenario Outline: Verify successful logins with multiple users
      Given user is in home page
      When user gives valid "<username>" and "<password>"
      Then user is taken to "Accounts Overview" page
      Examples:
        |username|password|
        | rob |demo|
        | joe |demo|

