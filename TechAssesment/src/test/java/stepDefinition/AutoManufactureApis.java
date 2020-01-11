package stepDefinition;

import java.util.List;

import commonResources.APIAutoCommon;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

public class AutoManufactureApis extends APIAutoCommon {


	@Given("^user hits the  manufacturer get call and gets the manufacturer details$")
	public void user_hits_the_manufacturer_get_call_and_gets_the_manufacturer_details() {
		try{
			Response res = getAutoManufacturerDetails();
			validateAutoManufacturer(res);
		}

		catch(Exception e){
			System.out.println(e.getMessage());

		}

	}

	@Given("^when user hits the manufacturer get call without the wakey the response returned is unauthorized\\.$")
	public void when_user_hits_the_manufacturer_get_call_without_the_wakey_the_response_returned_is_unauthorized() {

		try{
			Response res = getAutoManufacturerDetailsWithoutKey();
			validateAutoManufacturerWithoutKey(res);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

	@Given("^when user hits the manufacturer get call without the locale the response returned is bad request or internal server error\\.$")
	public void when_user_hits_the_manufacturer_get_call_without_the_locale_the_response_returned_is_bad_request_or_internal_server_error() {
		
		try{
			Response res = getAutoManufacturerDetailsWithoutLocale();
			validateAutoManufacturerWithoutLocale(res);
		}
		
		catch(Exception e){
			
		}

	}
	
	@Given("^when user hits the main types get call without the manufacturer code the response returned is a bad request\\.$")
	public void when_user_hits_the_main_types_get_call_without_the_manufacturer_code_the_response_returned_is_a_bad_request(){
	    
		try{
			Response res=getAutoMainTypesWithoutManufacturerCode();
			validateAutoMainTypesWithoutManufacturerCode(res);
		}
		
		catch(Exception e){
			
		}
	}

	@Given("^when user hits the main types get call without the locale but with the manufacturer code and wakey the response returned is unauthorized\\.$")
	public void when_user_hits_the_main_types_get_call_without_the_locale_but_with_the_manufacturer_code_and_wakey_the_response_returned_is_unauthorized(){
	    
		try{
			Response res=getAutoMainTypesWithoutLocale();
			validateAutoMainTypesWithoutLocale(res);
		}
		
		catch(Exception e){
			
		}
	}
	
	@Given("^when user hits the main types get call without the wakey but with the manufacturer code and locale the response returned is unauthorized\\.$")
	public void when_user_hits_the_main_types_get_call_without_the_wakey_but_with_the_manufacturer_code_and_locale_the_response_returned_is_unauthorized() throws Throwable {
	    
		try{
			Response res=getAutoMainTypesWithoutKey();
			validateAutoMainTypesWithoutKey(res);
	    }
	    
	    catch(Exception e){
	    	System.out.println(e);
	    }
	}
	
	@Given("^when user hits the main type api with all required parameters where manufacturer code is of two digits the response returned is bad request or internal server error\\.$")
	public void when_user_hits_the_main_type_api_with_all_required_parameters_where_manufacturer_code_is_of_two_digits_the_response_returned_is_bad_request_or_internal_server_error() {
	    
		try{
			Response res=getAutoMainTypesWithTwoDigitManufactureCode();
			validateWithTwoDigitOrInvalidThreeDigitManufactureCode(res);
	    }
	    
	    catch(Exception e){
	    	System.out.println(e);
	    }
	}

	@Given("^when user hits the main type api with arbitrary three digit manufacturer code that is not valid the response returned is a bad request or internal server error\\.$")
	public void when_user_hits_the_main_type_api_with_arbitrary_three_digit_manufacturer_code_that_is_not_valid_the_response_returned_is_a_bad_request_or_internal_server_error() {
	    
		try{
			Response res=getAutoMainTypesWithInvalidThreeDigitManufactureCode();
			validateWithTwoDigitOrInvalidThreeDigitManufactureCode(res);
	    }
	    
	    catch(Exception e){
	    	System.out.println(e);
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
