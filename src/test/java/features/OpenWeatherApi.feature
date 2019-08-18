@openWeatherApi
Feature: Open Weather API feature.
  This includes scenarios to test open weather api endpoints

  @api @get @end2end
  Scenario Outline: Verify GET weather api call
    Given I create request for GET weather call
    When I hit the GET endpoint with <city>
    Then I should expect <status> in reponse
    And I verify the data in the response

    Examples: 
      | city   | status |
      | London |    200 |
      | hgyts  |    404 |
