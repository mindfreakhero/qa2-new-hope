Feature: check if reservation is saved correctly;

  Scenario: check if reservation is saved correctly
    Given a new reservation with default data is created
    And we are requesting list of reservations
    And last reservation is found
    Then response contains the following:
      | name | surname | afrom | ato | bugs | discount | children | flight | adults | seat |
      | Anna | Vi      | RIX   | SVO | 1    | hello    | 0        | 17     | 3      | 22   |