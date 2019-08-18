@openWeatherGui
Feature: Open Weather GUI feature.
  This includes end to end scenarios to test open weather functionality

  @home @end2end
  Scenario: Verify the presence of key elements on Home page
    Given Url should be loaded
    Then I verify important elements on Home page

  @search @end2end
  Scenario Outline: Verify weather details on Search page
    Given Url should be loaded
    When I enter <city> in search box
    Then I should expect <data> on the Search page

    Examples: 
      | city       | data       |
      | London, UK | valid data |
      | hgyts      | Not found  |
