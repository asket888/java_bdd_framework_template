Feature: FRONTEND FEATURE SAMPLE
  Short summary should be present here

  Background:
    Given Start browser
    Given Open application main page
    Then Login into system as Super user
    Then "Main" page is displayed


  @frontEnd
  Scenario: FE-1: Check if all necessary elements are presented on the page
    Given User goes to "Tab1" -> "Page3" page
    Then "Cash History" page is displayed
    And Checks all following fields display on Page3 page
      |  From:                |
      |  To:                  |
      |  Select Certificate:  |