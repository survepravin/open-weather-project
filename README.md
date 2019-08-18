## Prudential test automation task
1. __Acceptance Criteria:__
     * __Write a first end-to-end test that__ 
       * Starts at https://openweathermap.org/
       * Verifies that all important information is there, e.g.  labels etc. (Give it a thought for what is important to test)
     * __Write a second end-to-end test that__ 
       * Starts on the https://openweathermap.org/
       * Enters an invalid city name
       * Searches for the weather
       * Verifies that website suggests city is "Not found"
     * __Write a last end-to-end test that__ 
       * Starts on the https://openweathermap.org/
       * Enters a valid city name
       * Searches for the weather
       * Verifies that website successfully returns weather details for the city.
    * __Write at least one additional test to validate the response through [OpenWeather API](https://openweathermap.org/api)__


## To run the automated tests
This automation framework is using *Selenium Webdriver* tool with *Cucumber* and also API testing using *RestAssured* library. To run the tests on your windows machine do the following.

#### STEPS
1. `mvn install`
2. `mvn test`

#### From Eclipse
Install all the dependencies, and once it is done. Run _'/src/test/java/testRunner/CucumberRunner.java'_ as JUnit, tests will start


## Framework architecture and design
Page Object Model(POM) with TestNG. According to the Page Object Model, you should keep the tests and element locators separately. This will keep the code clean and easy to understand and maintain.The Page Object approach makes automation framework in a testing programmer friendly, more durable and comprehensive. Another important advantage is our Page Object Repository is Independent of Automation Tests. If you keep a separate repository for page objects, it helps us to use this repository for different purposes with different frameworks like you will be able to integrate this repository with other tools like JUnit/TestNG/Cucumber/etc.

## Project Structure
### src/main/java/utils
Utility classes that can be used acrossed the project. *BrowserFactory.java* all browser can be configured here. *WebdriverWrapper.java* has common selenium common methods like explicit waits, etc.

### src/test/java/pages
Pages represents Application page, UI elements and methods are defined in these classes.

### src/test/java/testRunner
This includes Runner class.

### src/test/java/features
Feature files as per functionality, includes '*.feature*' files.

### src/test/java/stepDefinitions
Steps are implemented in this package.
