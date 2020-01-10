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
	private static Set<String> manufacturerCode = new HashSet<String>();
	private static Set<String> mainTypeCode = new HashSet<String>();

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
		endPoint = "v1/car-types/manufacturer?wa_key=coding-puzzle-client-449cc9d&locale=en";	
		response = RestAssured.given().
				when().log().all().headers(headers).get(endPoint);

		return response;
	}

	public List<Response> getAutoMainTypeDetails() throws Exception{

		String endPoint = null;
		HashMap<String, Object> headers	= new HashMap<String,Object>();
		headers = headersForms();
		List<Response> mainTypeResponse = null;
		RestAssured.baseURI = url;
		Iterator<String> it = manufacturerCode.iterator();
		while(it.hasNext()){
			String manCode = it.next();
			endPoint = "v1/car-types/manufacturer?wa_key=coding-puzzle-client-449cc9d&locale=en&manufacturer="+manCode;
			Response response = RestAssured.given().
					when().log().all().headers(headers).get(endPoint);
			mainTypeResponse.add(response);
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

	public List<Response> validateMainType(List<Response>resp) throws Exception{
		boolean isMatching = false;
		Map<String,String> mainTypeMap = new HashMap<String,String>();
		for(Response r : resp){
			JsonPath jsonPathEvaluator = r.jsonPath();
			int statusCode = r.getStatusCode();
			if (statusCode==200){
				mainTypeMap = jsonPathEvaluator.get("wkda");
				mainTypeCode = mainTypeMap.keySet();
				isMatching = true;
			}
		}
		Assert.assertTrue("Manufacturer Code is not matching",isMatching);
		return resp;

	}
}
