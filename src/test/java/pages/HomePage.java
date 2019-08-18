package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import utils.WebDriverWrapper;

/**
 * The Class HomePage.
 */
public class HomePage extends LoadableComponent<HomePage> {

	/** The lbl open weather logo. */
	@FindBy(xpath = "//img[@alt='openweather']")
	WebElement lblOpenWeatherLogo;

	/** The txt search city. */
	@FindBy(xpath = "//div[contains(@class, 'form-group')]/input")
	WebElement txtSearchCity;

	/** The btn search. */
	@FindBy(xpath = "//button[@type='submit' and @class='btn btn-orange']")
	WebElement btnSearch;

	/** The txt nav searchbox. */
	@FindBy(id = "nav-search")
	WebElement txtNavSearchbox;

	/** The lnk sign in. */
	@FindBy(xpath = "//a[contains(text(),'Sign In') and @class='pull-right']")
	WebElement lnkSignIn;

	/** The lnk sign up. */
	@FindBy(xpath = "//a[contains(text(),'Sign Up') and @class='pull-right']")
	WebElement lnkSignUp;

	/** The lbl units. */
	@FindBy(xpath = "//label[@class='toggle candy blue']/p")
	WebElement lblUnits;

	/** The lbl weather widget. */
	@FindBy(xpath = "//div[@class='widget__temperature']")
	WebElement lblWeatherWidget;

	/** The driver. */
	private WebDriver driver;

	/** The wrapper. */
	private WebDriverWrapper wrapper;

	/** The time out in seconds. */
	private final int timeOutInSeconds = 10;

	/**
	 * Instantiates a new home page.
	 *
	 * @param driver
	 *            the driver
	 * @param url
	 *            the url
	 */
	public HomePage(WebDriver driver, String url) {
		this.driver = driver;
		this.driver.get(url);
		PageFactory.initElements(driver, this);
		wrapper = new WebDriverWrapper(driver);
	}

	/*
	 * @see org.openqa.selenium.support.ui.LoadableComponent#isLoaded()
	 */
	@Override
	protected void isLoaded() throws Error {
		wrapper.waitForElementToVisible(txtSearchCity, timeOutInSeconds);
		txtSearchCity.isDisplayed();
	}

	/*
	 * @see org.openqa.selenium.support.ui.LoadableComponent#load()
	 */
	@Override
	protected void load() {
		wrapper.waitForElementToVisible(txtSearchCity, timeOutInSeconds);
		txtSearchCity.isDisplayed();
	}

	/**
	 * Click on search.
	 *
	 * @param cityName
	 *            the city name
	 * @return the search page
	 */
	public SearchPage searchWeatherByCityName(String cityName) {
		wrapper.waitForElementToVisible(txtSearchCity, timeOutInSeconds);
		txtSearchCity.sendKeys(cityName);
		btnSearch.click();
		return new SearchPage(driver).get();
	}

	/**
	 * Verify open weather logo.
	 *
	 * @return true, if successful
	 */
	public boolean verifyOpenWeatherLogo() {
		return lblOpenWeatherLogo.isDisplayed();
	}

	/**
	 * Verify sign in link.
	 *
	 * @return true, if successful
	 */
	public boolean verifySignInLink() {
		return lnkSignIn.isDisplayed();
	}

	/**
	 * Verify search city input box.
	 *
	 * @return true, if successful
	 */
	public boolean verifySearchCityInputBox() {
		return txtSearchCity.isDisplayed();
	}

	/**
	 * Verify navigation searchbox.
	 *
	 * @return true, if successful
	 */
	public boolean verifyNavigationSearchbox() {
		return txtNavSearchbox.isDisplayed();
	}

	/**
	 * Verify sign up link.
	 *
	 * @return true, if successful
	 */
	public boolean verifySignUpLink() {
		return lnkSignUp.isDisplayed();
	}

	/**
	 * Verify units widget.
	 *
	 * @return true, if successful
	 */
	public boolean verifyUnitsWidget() {
		return lblUnits.isDisplayed();
	}

	/**
	 * Verify weather widget.
	 *
	 * @return true, if successful
	 */
	public boolean verifyWeatherWidget() {
		return lblWeatherWidget.isDisplayed();
	}
}