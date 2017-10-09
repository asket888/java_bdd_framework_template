Feature: AMC INTERNAL SERVICES
  Test if all AMC internal services work correctly

  Background:
    Given Login into Application as SuperUser
    Then "Main" page is displayed


  @backend
  Scenario Outline: IS-1: Verify that all external services respond correct status code
    Given Sent GET http request on External service "<Endpoint>"
    Then Receive response code "200" from External service
    Examples:
      | Endpoint                                     |
      | http://application.com:9001/system/heartbeat |
      | http://application.com:9002/system/heartbeat |
      | http://application.com:9003/system/heartbeat |

  @backend
  Scenario: IS-2: Verify that main 'Portfolio Elements' service sent response for COI update
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