package stepDefinition;

import java.util.List;

import commonUtil.API_AutoCommon;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

public class AutoManufactureApis extends API_AutoCommon {


	@Given("^user hits the the manufacturer get call and gets the manufacturer details$")
	public void user_hits_the_the_manufacturer_get_call_and_gets_the_manufacturer_details() {
		try{
			Response res = getAutoManufacturerDetails();
			validateAutoManufacturer(res);
		}

		catch(Exception e){
			System.out.println(e.getMessage());

		}

	}

	@When("^user gets the manufacturer details and hits the car type get call$")
	public void user_gets_the_manufacturer_details_and_hits_the_car_type_get_call() {

		try{
			List<Response> mainTypeRes = getAutoMainTypeDetails();
			validateMainType(mainTypeRes);
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	@Then("^user enters the manufacturer details and the main type details and hits the built in date$")
	public void user_enters_the_manufacturer_details_and_the_main_type_details_and_hits_the_built_in_date() throws Throwable {


	}
}
