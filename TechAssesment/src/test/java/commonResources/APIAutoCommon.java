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


	protected String url = CommonUtilities.baseUrl;
	private static Set<String> manufacturerCode = new HashSet<String>();
	private static Set<String> mainTypeCode = new HashSet<String>();
	private static List<Set<String>> mainTypeCodes = new ArrayList<Set<String>>();

	public HashMap<String, Object> headersForms(){	
		HashMap<String, Object> headers = new HashMap<String,Object>();
		headers.put("Content-Type", "application/json");
		return headers;
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

	public boolean validateAutoManufacturer(Response resp) throws Exception{

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
		return isMatching;


	}

	public boolean validateAutoManufactureAndMainTypesWithoutKey(Response resp) throws Exception{

		boolean isMatching = false;
		int statusCode = resp.getStatusCode();
		if (statusCode==401) {				
			isMatching = true;
		}

		Assert.assertTrue("Unauthorized Response For Manufacturer is not shown up",isMatching);
		return isMatching;

	}

	public boolean validateAutoMainTypesWithoutManufacturerCode(Response resp) throws Exception{

		boolean isMatching = false;
		JsonPath jsonPathEvaluator = resp.jsonPath();
		int statusCode = resp.getStatusCode();
		String error = jsonPathEvaluator.get("error");
		String message = jsonPathEvaluator.get("message");
		if (statusCode==400 && error.equalsIgnoreCase("Bad Request") && message.equalsIgnoreCase("Required String parameter 'manufacturer' is not present")) {				
			isMatching = true;
		}

		Assert.assertTrue("Unauthorized Response For Main Types is not shown up", isMatching);
		return isMatching;

	}

	public boolean validateAutoManufacturerandMainTypeWithoutLocale(Response resp) throws Exception{

		boolean isMatching = false;
		int statusCode = resp.getStatusCode();
		if (statusCode==400 || statusCode==500) {				
			isMatching = true;
		}

		Assert.assertTrue("Bad Request Or Internal Server error is not showing up in the response",isMatching);
		return isMatching;

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

	public String getManufactureEndpoint(String ... args)
	{

		if(args[2].equals("1")){
			return "v1/car-types/manufacturer?wa_key=" + args[0] + "&locale=" + args[1];
		}

		else if(args[2].equals("2")){

			return "v1/car-types/manufacturer?wa_key=" + args[0] ;
		}

		else{
			return "v1/car-types/manufacturer?locale=" + args[1];
		}

	}

	public String getMainTypeEndpoint(String ... args)
	{

		if(args[3].equals("1")){
			return "v1/car-types/main-types?manufacturer=" +args[2] + "&wa_key=" + args[0] + "&locale=" + args[1];
		}

		else if(args[3].equals("2")){

			return "v1/car-types/main-types?wa_key=" + args[0] + "&locale=" + args[1];
		}

		else if(args[3].equals("3")){

			return "v1/car-types/main-types?manufacturer=" +args[2] + "&locale=" + args[1];
		}

		else{
			return "v1/car-types/main-types?manufacturer=" +args[2] + "&wa_key=" + args[0];
		}

	}

	public Response sendManufactureRequest(String baseUri, String endpoint) throws Exception{
		HashMap<String, Object> headers	= new HashMap<String,Object>();
		headers = headersForms();
		Response response = null;
		RestAssured.baseURI = baseUri;
		response = RestAssured.given().
				when().log().all().headers(headers).get(endpoint);

		return response;
	}


}


