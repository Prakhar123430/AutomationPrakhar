package commonUtil;

import java.io.FileReader;
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

public class API_AutoCommon {

	String url = "http://api-aws-eu-qa-1.auto1-test.com";
	String url_manufacturer = "v1/car-types/manufacturer?wa_key=coding-puzzle-client-449cc9d&locale=en";
	Map<String,String> manufacturer_Map = new HashMap<String,String>();
	Set<String> manufacturer_code = new HashSet<String>();

	public HashMap<String, Object> headersForms(){		
		HashMap<String, Object> headers = new HashMap<String,Object>();
		headers.put("Content-Type", "application/json");
		return headers;
	}


	public Response auto(String str, String str1) throws Exception{
		String endPoint = null;
		String params = null; 
		HashMap<String, Object> headers	= new HashMap<String,Object>();
		headers = headersForms();
		Response response = null;

		if(str.equalsIgnoreCase("ManufacturerOfAuto")) {
			RestAssured.baseURI = url;
			endPoint = url_manufacturer;	
			response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);

		}
		
		return response;
	}

	public Response validation_Auto(String str, Response resp) throws Exception{

		boolean isMatching = false;
		String status="";
		String description="";
		int statusCode = resp.getStatusCode();
		JsonPath jsonPathEvaluator = resp.jsonPath();
		if (str.equalsIgnoreCase("ManufacturerOfAuto")) {				
			if(statusCode==200){
				manufacturer_Map = jsonPathEvaluator.get("wkda");
				manufacturer_code = manufacturer_Map.keySet();
				isMatching = true;
			}
		}

		Assert.assertTrue(isMatching);
		return resp;


	}

}

