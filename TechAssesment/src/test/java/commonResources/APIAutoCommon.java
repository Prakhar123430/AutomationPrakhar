package commonResources;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class APIAutoCommon {
	
	
	String url = CommonUtilities.baseUrl;
	private static Set<String> manufacturerCode = new HashSet<String>();
	private static Set<String> mainTypeCode = new HashSet<String>();
	private static List<Set<String>> mainTypeCodes = new ArrayList<Set<String>>();

	public HashMap<String, Object> headersForms(){	
		HashMap<String, Object> headers = new HashMap<String,Object>();
		headers.put("Content-Type", "application/json");
		return headers;
	}


	public Response getAutoManufacturerDetails() throws Exception{
		String endPoint = null;
		HashMap<String, Object> headers	= new HashMap<String,Object>();
		headers = headersForms();
		Response response = null;
		RestAssured.baseURI = url;
		endPoint = "/v1/car-types/manufacturer?wa_key="+CommonUtilities.waKey+"&locale="+CommonUtilities.locale;
		String newURL = url+endPoint;
		response = RestAssured.given().
				when().log().all().headers(headers).get(newURL);

		return response;
	}

	public Response getAutoManufacturerDetailsWithoutKey() throws Exception{
		String endPoint = null;
		HashMap<String, Object> headers	= new HashMap<String,Object>();
		headers = headersForms();
		Response response = null;
		RestAssured.baseURI = url;
		endPoint = "/v1/car-types/manufacturer?locale="+CommonUtilities.locale;
		String newURL = url+endPoint;
		response = RestAssured.given().
				when().log().all().headers(headers).get(newURL);

		return response;
	}

	public Response getAutoManufacturerDetailsWithoutLocale() throws Exception{
		String endPoint = null;
		Response response = null;
		RestAssured.baseURI = url;
		HashMap<String, Object> headers	= new HashMap<String,Object>();
		headers = headersForms();
		endPoint = "/v1/car-types/manufacturer?wa_key="+CommonUtilities.waKey;	
		String newURL = url+endPoint;
		response = RestAssured.given().
				when().log().all().headers(headers).get(newURL);

		return response;
	}

	public Response getAutoMainTypesWithoutManufacturerCode() throws Exception{
		String endPoint = null;
		Response response = null;
		RestAssured.baseURI = url;
		HashMap<String, Object> headers	= new HashMap<String,Object>();
		headers = headersForms();
		endPoint = "/v1/car-types/main-types?locale="+CommonUtilities.locale+"&wa_key="+CommonUtilities.waKey;	
		String newURL = url+endPoint;
		response = RestAssured.given().
				when().log().all().headers(headers).get(newURL);

		return response;
	}

	public Response getAutoMainTypesWithoutLocale() throws Exception{
		String endPoint = null;
		Response response = null;
		RestAssured.baseURI = url;
		HashMap<String, Object> headers	= new HashMap<String,Object>();
		headers = headersForms();
		endPoint = "/v1/car-types/main-types?manufacturer=020&wa_key="+CommonUtilities.waKey;	
		String newURL = url+endPoint;
		response = RestAssured.given().
				when().log().all().headers(headers).get(newURL);

		return response;
	}

	public Response getAutoMainTypesWithoutKey() throws Exception{
		String endPoint = null;
		Response response = null;
		RestAssured.baseURI = url;
		HashMap<String, Object> headers	= new HashMap<String,Object>();
		headers = headersForms();
		endPoint = "/v1/car-types/main-types?manufacturer=020&locale="+CommonUtilities.locale;	
		String newURL = url+endPoint;
		response = RestAssured.given().
				when().log().all().headers(headers).get(newURL);

		return response;
	}

	public Response getAutoMainTypesWithTwoDigitManufactureCode() throws Exception{
		String endPoint = null;
		Response response = null;
		RestAssured.baseURI = url;
		HashMap<String, Object> headers	= new HashMap<String,Object>();
		headers = headersForms();
		endPoint = "/v1/car-types/main-types?manufacturer=20&locale="+CommonUtilities.locale+"&wa_key="+CommonUtilities.waKey;	
		String newURL = url+endPoint;
		response = RestAssured.given().
				when().log().all().headers(headers).get(newURL);

		return response;
	}
	
	public Response getAutoMainTypesWithInvalidThreeDigitManufactureCode() throws Exception{
		String endPoint = null;
		Response response = null;
		RestAssured.baseURI = url;
		HashMap<String, Object> headers	= new HashMap<String,Object>();
		headers = headersForms();
		endPoint = "/v1/car-types/main-types?manufacturer=123&locale="+CommonUtilities.locale+"&wa_key="+CommonUtilities.waKey;	
		String newURL = url+endPoint;
		response = RestAssured.given().
				when().log().all().headers(headers).get(newURL);

		return response;
	}

	public List<Response> getAutoMainTypeDetails() throws Exception{

		String endPoint = null;
		HashMap<String, Object> headers	= new HashMap<String,Object>();
		headers = headersForms();
		List<Response> mainTypeResponse = new ArrayList<Response>();
		RestAssured.baseURI = url;
		Iterator<String> it = manufacturerCode.iterator();
		while(it.hasNext()){
			try{
				String manCode = it.next();
				endPoint = "/v1/car-types/manufacturer?wa_key=coding-puzzle-client-449cc9d&locale=en&manufacturer="+manCode;
				Response response = RestAssured.given().
						when().log().all().headers(headers).get(endPoint);
				mainTypeResponse.add(response);
			}
			catch(Exception e){
				System.out.println(e);
			}
		}

		return mainTypeResponse;

	}

	public Response validateAutoManufacturer(Response resp) throws Exception{

		boolean isMatching = false;
		int statusCode = resp.getStatusCode();
		JsonPath jsonPathEvaluator = resp.jsonPath();
		Map<String,String> manufacturerMap = new HashMap<String,String>();
		if (statusCode==200) {				
			manufacturerMap = jsonPathEvaluator.get("wkda");
			manufacturerCode = manufacturerMap.keySet();
			isMatching = true;
		}

		Assert.assertTrue("Manufacturer Code is not matching",isMatching);
		return resp;


	}

	public Response validateAutoManufacturerWithoutKey(Response resp) throws Exception{

		boolean isMatching = false;
		int statusCode = resp.getStatusCode();
		if (statusCode==401) {				
			isMatching = true;
		}

		Assert.assertTrue("Unauthorized Response For Manufacturer is not shown up",isMatching);
		return resp;

	}
	
	public Response validateAutoManufacturerWithoutLocale(Response resp) throws Exception{

		boolean isMatching = false;
		int statusCode = resp.getStatusCode();
		if (statusCode==400 || statusCode==500) {				
			isMatching = true;
		}

		Assert.assertTrue("Bad Request Or Internal server error is not showing up",isMatching);
		return resp;

	}

	public Response validateAutoMainTypesWithoutManufacturerCode(Response resp) throws Exception{

		boolean isMatching = false;
		JsonPath jsonPathEvaluator = resp.jsonPath();
		int statusCode = resp.getStatusCode();
		String error = jsonPathEvaluator.get("error");
		String message = jsonPathEvaluator.get("message");
		if (statusCode==400 && error.equalsIgnoreCase("Bad Request") && message.equalsIgnoreCase("Required String parameter 'manufacturer' is not present")) {				
			isMatching = true;
		}

		Assert.assertTrue("Unauthorized Response For Main Types is not shown up", isMatching);
		return resp;

	}

	public Response validateAutoMainTypesWithoutKey(Response resp) throws Exception{

		boolean isMatching = false;
		int statusCode = resp.getStatusCode();
		if (statusCode==401) {				
			isMatching = true;
		}

		Assert.assertTrue("Unauthorized Response For Manufacturer is not shown up",isMatching);
		return resp;

	}
	
	public Response validateAutoMainTypesWithoutLocale(Response resp) throws Exception{

		boolean isMatching = false;
		int statusCode = resp.getStatusCode();
		if (statusCode==400 || statusCode==500) {				
			isMatching = true;
		}

		Assert.assertTrue("Bad Request Or Internal Server error is not showing up in the response",isMatching);
		return resp;

	}

	public Response validateWithTwoDigitOrInvalidThreeDigitManufactureCode(Response resp) throws Exception{

		boolean isMatching = false;
		int statusCode = resp.getStatusCode();
		if (statusCode==400 || statusCode==500) {				
			isMatching = true;
		}

		Assert.assertTrue("Status Code is returning 200", isMatching);
		return resp;

	}


	public List<Response> validateMainType(List<Response>resp) throws Exception{
		Map<String,String> mainTypeMap = new HashMap<String,String>();
		for(Response r : resp){
			boolean isMatching = false;
			JsonPath jsonPathEvaluator = r.jsonPath();
			int statusCode = r.getStatusCode();
			if (statusCode==200){
				mainTypeMap = jsonPathEvaluator.get("wkda");
				mainTypeCode = mainTypeMap.keySet();
				isMatching = true;
			}
			Assert.assertEquals(statusCode, 200);
			Assert.assertNotNull("wkda is null",jsonPathEvaluator.get("wkda"));
		}

		return resp;

	}
}
