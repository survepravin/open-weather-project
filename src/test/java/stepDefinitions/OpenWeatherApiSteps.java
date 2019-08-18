package stepDefinitions;

import java.util.HashMap;

import org.junit.Assert;

import api.CommonApi;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import utils.ConfigProperties;
import utils.Encryptor;

/**
 * The Class OpenWeatherApiSteps.
 */
public class OpenWeatherApiSteps {

	/** The base url. */
	String baseUrl = ConfigProperties.getKeyValue("API_BASE_URL");

	/** The get weather url. */
	String getWeatherUrl = ConfigProperties.getKeyValue("API_GET_URL");

	/** The app id. */
	String appId = ConfigProperties.getKeyValue("API_APP_ID");

	/** The headers. */
	HashMap<String, String> headers;

	/** The response. */
	Response response;

	/** The url. */
	String url = "";

	/** The city name. */
	String cityName = "";

	/**
	 * I create request for GE T weather call.
	 */
	@Given("I create request for GET weather call")
	public void i_create_request_for_GET_weather_call() {
		// Decrypt the API key
		appId = Encryptor.decrypt(appId);
		
		// Set headers
		headers = new HashMap<String, String>();
		headers.put("Accept", "application/json");

		// Set base url
		CommonApi.setBaseUrl(baseUrl);
		url = getWeatherUrl;
	}

	/**
	 * I hit GE T call with city name.
	 *
	 * @param cityName
	 *            the city name
	 */
	@When("I hit the GET endpoint with ([^\"]*)")
	public void i_hit_GET_call_with_cityName(String cityName) {
		this.cityName = cityName;
		url = url.replace("{APPID}", appId).replace("{CITY}", cityName);

		// make a get call
		response = CommonApi.GET(url, headers);
	}

	/**
	 * I should expect O K status.
	 *
	 * @param status
	 *            the status
	 */
	@Then("I should expect ([^\"]*) in reponse")
	public void i_should_expect_OK_status(int status) {
		Assert.assertEquals(response.statusCode(), status);
		Assert.assertEquals(response.getBody().jsonPath().getString("cod"), status + "");
	}

	/**
	 * I verify the data in the response.
	 */
	@And("I verify the data in the response")
	public void i_verify_the_data_in_the_response() {
		if (200 == response.statusCode()) {
			Assert.assertEquals(response.getBody().jsonPath().getString("name"), cityName);
		} else {
			Assert.assertEquals(response.getBody().jsonPath().getString("message"), "city not found");
		}
	}
}
