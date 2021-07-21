Feature: check if reservation is saved correctly;

  Scenario: check if reservation is saved correctly
  #sna4ala preconditions, chobi ih mozhno bilo menjatj vhodnije dannije
    Given flight from "RIX" to "SVO"
    And passenger info:
      | name     | Anna       |
      | surname  | Vi         |
      | discount | CCCCCC     |
      | adults   | 3          |
      | children | 2          |
      | luggage  | 1          |
      | flight   | 17-05-2018 |
    And seat number is: 22

    When we are opening home page
    And selecting airports
    Then airports are displayed on second page

#    When we are submitting passenger info
#    Then name appears in summary
#    And price calculated: 3020 EUR
#    And reservation number appears
#
#    When we are pressing book button
#    And selection seat number 22
#    Then seat number appears on page
#
#    When we are booking flight
#    Then success message appears

    When we are requesting list of reservations
    And last reservation is found
    Then response contains the following:
      | name | surname | afrom | ato | bugs | discount | children | flight | adults | seat |
      | Anna | Vi      | RIX   | SVO | 1    | CCCCCC   | 0        | 17     | 3      | 22   |