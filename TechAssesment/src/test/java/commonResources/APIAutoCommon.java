package commonResources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;

import cucumber.api.DataTable;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class APIAutoCommon {


	protected String url = CommonUtilities.baseUrl;
	private Set<String> manufacturerCode = new HashSet<String>();
	private Set<String> mainTypeCode = new HashSet<String>();
	private List<ManufacturerAndMainTypeCodes> manufacturerAndMainTypeCode = new ArrayList<ManufacturerAndMainTypeCodes>();

	public HashMap<String, Object> headersForms(){	
		HashMap<String, Object> headers = new HashMap<String,Object>();
		headers.put("Content-Type", "application/json");
		return headers;
	}

	public List<Response> getAutoMainTypeDetails() throws Exception{

		String endPoint = null;
		HashMap<String,Object> headers	= new HashMap<String,Object>();
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

	public boolean validateAutoManufacturerMainTypeAndBuiltIn(Response resp) throws Exception{

		boolean isMatching=false;
		int statusCode = resp.getStatusCode();
		isMatching = CommonUtilities.statusCode200(statusCode,isMatching);
		Assert.assertTrue("Manufacturer Code is not matching",isMatching);
		return isMatching;


	}

	public boolean validateAutoManufactureMainAndBuiltInWithoutKey(Response resp) throws Exception{

		boolean isMatching = false;
		int statusCode = resp.getStatusCode();
		isMatching = CommonUtilities.statusCode401(statusCode);
		Assert.assertTrue("Unauthorized Response is not shown up",	isMatching);
		return isMatching;

	}
	
	public boolean validateAutoManufactureMainAndBuiltInWithAnInvalidKey(Response resp) throws Exception{

		boolean isMatching = false;
		int statusCode = resp.getStatusCode();
		isMatching = CommonUtilities.statusCode403(statusCode);
		Assert.assertTrue("Forbidden Response is not shown up",	isMatching);
		return isMatching;

	}
	

	public boolean validateAutoMainTypesAndBuiltInWithoutManufacturerCodeAndMainType(Response resp) throws Exception{

		boolean isMatching = false;
		JsonPath jsonPathEvaluator = resp.jsonPath();
		int statusCode = resp.getStatusCode();
		String error = jsonPathEvaluator.get("error");
		String message = jsonPathEvaluator.get("message");
		isMatching = CommonUtilities.badRequest(statusCode, error, message);
		Assert.assertTrue("Unauthorized Response For Main Types is not shown up", isMatching);
		return isMatching;

	}

	public boolean validateAutoManufacturerMainAndBuiltInWithoutLocale(Response resp) throws Exception{

		boolean isMatching=false;
		int statusCode = resp.getStatusCode();
		isMatching = CommonUtilities.badRequestOrInternalServerError(statusCode);
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

	public String getBuiltInEndpoint(String ... args)
	{

		if(args[4].equals("1")){
			return "v1/car-types/built-dates?manufacturer=" +args[2] + "&wa_key=" + args[0] + "&locale=" + args[1] +  "&main-type=" + args[3];
		}

		else if(args[4].equals("2")){

			return "v1/car-types/built-dates?wa_key=" + args[0] + "&locale=" + args[1] +  "&main-type=" + args[3];
		}

		else if(args[4].equals("3")){

			return "v1/car-types/built-dates?manufacturer=" +args[2]  + "&locale=" + args[1] +  "&main-type=" + args[3];
		}

		else if(args[4].equals("4")){

			return "v1/car-types/built-dates?manufacturer=" +args[2] + "&wa_key=" + args[0] +  "&main-type=" + args[3];
		}

		else if(args[4].equals("6")){

			return "v1/car-types/built-dates?wa_key=" + args[0] + "&locale=" + args[1];
		}

		else{

			return "v1/car-types/built-dates?manufacturer=" +args[2] + "&wa_key=" + args[0] + "&locale=" + args[1];

		}

	}

	public List<String> getCumulativeMismatchAfterResponse(String baseUrl, DataTable queryParams, Set<String> codes) throws Exception
	{
		List<String> errorList = new ArrayList<String>();
		List<List<String>> data = queryParams.raw();

		for(String code : codes)
		{
			String endpoint = getMainTypeEndpoint(data.get(0).get(0), data.get(0).get(1),code,data.get(0).get(2));
			Response resp = sendRequest(baseUrl, endpoint);
			HashMap<String,String> mainType = resp.jsonPath().get("wkda");
			Set<String> mainTypeCode=mainType.keySet();
			for(String m : mainTypeCode){
				ManufacturerAndMainTypeCodes obj = new ManufacturerAndMainTypeCodes(code, m);
				manufacturerAndMainTypeCode.add(obj);
			}
			if(validateAutoManufacturerMainTypeAndBuiltIn(resp)==false)
				errorList.add("Response mismatch for "+code);
		}
		return errorList;
	}

	public Response sendRequest(String baseUri, String endpoint) throws Exception{
		HashMap<String, Object> headers	= new HashMap<String,Object>();
		headers = headersForms();
		Response response = null;
		RestAssured.baseURI = baseUri;
		response = RestAssured.given().
				when().log().all().headers(headers).get(endpoint);

		return response;
	}


	public Set<String> getManufacturerCode(Response resp) throws Exception{
		Set<String> manufacturerCodes = new HashSet<String>();
		Map<String,String> manufacturerMap = new HashMap<String,String>();
		JsonPath jsonPathEvaluator = resp.jsonPath();
		manufacturerMap = jsonPathEvaluator.get("wkda");
		manufacturerCodes = manufacturerMap.keySet();
		return manufacturerCodes;
	}



	public List<String> getCumulativeMismatchForBuiltInAfterResponse(String baseUrl, DataTable queryParams) throws Exception{
		List<String> errorList = new ArrayList<String>();
		List<List<String>> data = queryParams.raw();
		for(ManufacturerAndMainTypeCodes m: manufacturerAndMainTypeCode){
			String endpoint = getBuiltInEndpoint(data.get(0).get(0), data.get(0).get(1),m.manufacturerCode,m.mainTypeCode,data.get(0).get(2));
			Response resp = sendRequest(baseUrl, endpoint);
			if(validateAutoManufacturerMainTypeAndBuiltIn(resp)==false)
				errorList.add("Response mismatch for "+m.mainTypeCode);
		}
		return errorList;
	}
}




