package stepDefinition;

import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import commonResources.APIAutoCommon;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import junit.framework.Assert;

public class AutoManufactureApis extends APIAutoCommon {


	String baseUri;
	String endpoint;
	Response resp;

	@Given("^user sets the GET baseURL$")
	public void user_sets_the_GET_baseURL() throws Exception {
		try
		{
			baseUri = url;
		}
		catch(Exception e)
		{
			Logger.getLogger(e.getMessage());
		}
	}

	@When("^user appends the query parameters$")
	public void user_appends_the_query_parameters(DataTable queryParams) throws Exception {
		try
		{
			List<List<String>> data = queryParams.raw();
			endpoint = getManufactureEndpoint(data.get(0).get(0), data.get(0).get(1), data.get(0).get(2));
		}
		catch(Exception e)
		{
			Logger.getLogger(e.getMessage());
		}
	}

	@When("^sends a GET http request$")
	public void sends_a_GET_http_request() throws Exception {
		try
		{
			resp = sendManufactureRequest(baseUri, endpoint);
		}
		catch(Exception e)
		{
			Logger.getLogger(e.getMessage());
		}

	}

	@Then("^user validates the response$")
	public void user_validates_the_response() throws Exception {
		try
		{
			Assert.assertTrue("Invalid response received", validateAutoManufacturer(resp)==true);
		}
		catch(Exception e)
		{
			Logger.getLogger(e.getMessage());
		}
	}

	@Then("^user validates the response is unauthorized$")
	public void user_validates_the_response_is_unauthorized() throws Exception {

		try{

			Assert.assertTrue("Invalid response received", validateAutoManufactureAndMainTypesWithoutKey(resp)==true);
		}

		catch(Exception e){
			Logger.getLogger(e.getMessage());
		}

	}

	@Then("^user validates the response is a bad request or internal server error$")
	public void user_validates_the_response_is_a_bad_request_or_internal_server_error() throws Exception {

		try{

			Assert.assertTrue("Invalid response received", validateAutoManufacturerandMainTypeWithoutLocale(resp)==true);

		}

		catch(Exception e){
			Logger.getLogger(e.getMessage());
		}
	}

	@When("^user appends the query parameters for maintype$")
	public void user_appends_the_query_parameters_for_maintype(DataTable queryParams) throws Exception {

		try{
			List<List<String>> data = queryParams.raw();
			endpoint = getMainTypeEndpoint(data.get(0).get(0), data.get(0).get(1), data.get(0).get(2),data.get(0).get(3));
		}

		catch(Exception e){
			Logger.getLogger(e.getMessage());
		}

	}

	@Then("^user validates that the response is a bad request or internal server error$")
	public void user_validates_that_the_response_is_a_bad_request_or_internal_server_error() throws Exception {

		try{

			Assert.assertTrue("Invalid response received", validateAutoMainTypesWithoutManufacturerCode(resp)==true);
		}
		catch(Exception e){
			Logger.getLogger(e.getMessage());
		}
	}


	// ***************************** End Of Test Scenarios**************************






	@When("^user gets the manufacturer details and hits the car type get call$")
	public void user_gets_the_manufacturer_details_and_hits_the_car_type_get_call() {

		try{
			List<Response> mainTypeRes = getAutoMainTypeDetails();
			validateMainType(mainTypeRes);
		}
		catch(Exception e){
			Logger.getLogger(e.getMessage());
		}
	}
}

