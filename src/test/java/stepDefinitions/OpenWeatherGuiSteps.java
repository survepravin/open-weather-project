package stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.SearchPage;
import utils.BrowserFactory;
import utils.ConfigProperties;

/**
 * The Class OpenWeatherSteps.
 */
public class OpenWeatherGuiSteps {

	/** The driver. */
	private WebDriver driver;

	/** The home page. */
	private HomePage homePage;

	/** The search page. */
	private SearchPage searchPage;

	/**
	 * Url should be loaded.
	 */
	@Given("Url should be loaded")
	public void url_should_be_loaded() {
		driver = BrowserFactory.getDriver(ConfigProperties.getKeyValue("BROWSER"));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		homePage = new HomePage(driver, ConfigProperties.getKeyValue("BASE_URL"));
	}

	/**
	 * I verify important elements on home page.
	 */
	@Then("I verify important elements on Home page")
	public void i_verify_important_elements_on_Home_page() {
		Assert.assertEquals(homePage.verifyNavigationSearchbox(), true);
		Assert.assertEquals(homePage.verifyOpenWeatherLogo(), true);
		Assert.assertEquals(homePage.verifySearchCityInputBox(), true);
		Assert.assertEquals(homePage.verifySignInLink(), true);
		Assert.assertEquals(homePage.verifySignUpLink(), true);
		Assert.assertEquals(homePage.verifyUnitsWidget(), true);
		Assert.assertEquals(homePage.verifyWeatherWidget(), true);
	}

	/**
	 * I enter city in search box.
	 *
	 * @param cityName
	 *            the city name
	 */
	@When("I enter ([^\"]*) in search box")
	public void i_enter_city_in_search_box(String cityName) {
		searchPage = homePage.searchWeatherByCityName(cityName);
	}

	/**
	 * I should expect data on the search page.
	 *
	 * @param data
	 *            the data
	 */
	@Then("I should expect ([^\"]*) on the Search page")
	public void i_should_expect_data_on_the_Search_page(String data) {
		if (data.toUpperCase().contains("NOT FOUND")) {
			Assert.assertEquals(searchPage.isDataNotFound(), true);
		} else {
			Assert.assertEquals(searchPage.getCityName().length() > 0, true);
			Assert.assertEquals(searchPage.getTemperatureOfCity().length() > 0, true);
			Assert.assertEquals(searchPage.getWeatherCondition().length() > 0, true);
		}
	}

	/**
	 * After.
	 */
	@After
	public void after() {
		if (driver != null) {
			driver.quit();
		}
	}
}
