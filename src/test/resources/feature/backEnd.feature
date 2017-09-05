Feature: AMC INTERNAL SERVICES
  Test if all AMC internal services work correctly

  Background:
    Given Start browser
    Given Open application main page
    Then Login into system as Super user
    Then "Main" page is displayed

  @backEnd
  Scenario: IS-1: Verify that main 'Portfolio Elements' service sent response for COI update
    Given Check Internal service endpoint "rest/path/element123" response "200"
    Given Send POST request with following json body to endpoint "rest/path/element123"
      | {                          |
      | "id":"0001006552",         |
      | "region":"EMEA",           |
      | "value":"USD"              |
      | }                          |

    Then Following response Json file received from the Internal service
      | {                          |
      | "message":"Elem Updated",  |
      | "id":"0001006552",         |
      | "region":"EMEA",           |
      | "value":"USD"              |
      | }                          |