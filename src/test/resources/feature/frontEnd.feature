Feature: FRONTEND FEATURE SAMPLE
  Short summary should be present here

  Background:
    Given Login into Application as SuperUser
    Then "Main" page is displayed


  @frontend
  Scenario: FE-1: Check if all necessary elements are presented on the page
    Given User goes to "Tab1" -> "Page3" page
    Then "Page3" page is displayed
    And Checks all following fields display on Page3 page
      | From:           |
      | To:             |
      | Select Element: |

    And Checks all following buttons display on Page3 page
      | Submit          |
      | Cancel          |


    @frontend
    Scenario: FE-2: Check Page3 feature for correct time period
      Given User goes to "Tab1" -> "Page3" page
      Then "Page3" page is displayed
      And User fills all fields on Page3 page with following data
        | From:           | 01 January 2017 |
        | To:             | 01 October 2017 |
        | Select Element: | Element№3      |

      When User clicks 'Submit' button on Page3 page
      Then 15 events are displayed in Result block