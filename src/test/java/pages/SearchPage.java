package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import utils.WebDriverWrapper;

/**
 * The Class MenuPage.
 */
public class SearchPage extends LoadableComponent<SearchPage> {

	/** The lbl not found. */
	@FindBy(xpath = "//div[@role='alert'] | //div[text()='Not found']")
	WebElement lblNotFound;

	/** The lbl header. */
	@FindBy(xpath = "//h2")
	WebElement lblHeader;

	/** The tbl forecast list. */
	@FindBy(xpath = "//div[@id='forecast_list_ul']/table")
	WebElement tblForecastList;

	/** The lbl city name. */
	@FindBy(xpath = "//div[@id='forecast_list_ul']/table/tbody/tr[1]/td[2]/b[1]/a")
	WebElement lblCityName;

	/** The lbl weather condition. */
	@FindBy(xpath = "//div[@id='forecast_list_ul']/table/tbody/tr[1]/td[2]/b[2]/i")
	WebElement lblWeatherCondition;

	/** The lbl city temperature. */
	@FindBy(xpath = "//div[@id='forecast_list_ul']/table/tbody/tr[1]/td[2]/p/span")
	WebElement lblCityTemperature;

	/** The driver. */
	@SuppressWarnings("unused")
	private WebDriver driver;

	/** The wrapper. */
	private WebDriverWrapper wrapper;

	/** The header text. */
	private final String HEADER_TEXT = "Weather in your city";

	/** The time out in seconds. */
	private final int timeOutInSeconds = 10;

	/**
	 * Instantiates a new Search page.
	 *
	 * @param driver
	 *            the driver
	 */
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}

	/*
	 * @see org.openqa.selenium.support.ui.LoadableComponent#isLoaded()
	 */
	@Override
	protected void isLoaded() throws Error {
		wrapper.waitForElementToVisible(lblHeader, timeOutInSeconds);
		isHeaderVisible();
	}

	/*
	 * @see org.openqa.selenium.support.ui.LoadableComponent#load()
	 */
	@Override
	protected void load() {
		wrapper.waitForElementToVisible(lblHeader, timeOutInSeconds);
		isHeaderVisible();
	}

	/**
	 * Checks if is header visible.
	 *
	 * @return true, if is header visible
	 */
	public boolean isHeaderVisible() {
		String header = lblHeader.getText();
		return HEADER_TEXT.equals(header);
	}

	/**
	 * Gets the city name.
	 *
	 * @return the city name
	 */
	public String getCityName() {
		wrapper.waitForElementToVisible(tblForecastList, timeOutInSeconds);
		return lblCityName.getText();
	}

	/**
	 * Gets the weather condition.
	 *
	 * @return the weather condition
	 */
	public String getWeatherCondition() {
		return lblWeatherCondition.getText();
	}

	/**
	 * Gets the temperature of city.
	 *
	 * @return the temperature of city
	 */
	public String getTemperatureOfCity() {
		return lblCityTemperature.getText();
	}

	/**
	 * Checks if is data not found.
	 *
	 * @return true, if is data not found
	 */
	public boolean isDataNotFound() {
		return lblNotFound.isDisplayed();
	}
}