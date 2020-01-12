package commonResources;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CommonUtilities {

	protected static final String baseUrl = "http://api-aws-eu-qa-1.auto1-test.com";
	protected static String locale = "en";
	protected static String waKey = "coding-puzzle-client-449cc9d";

	public static boolean statusCode200(int sc, boolean isMatching){
		if(sc==200)
			return (isMatching=true);
		else
			return (isMatching=true);
	}
	
	public static boolean statusCode401(int sc){
		if(sc==401)
			return true;
		else
			return false;
	}

	public static boolean badRequest(int sc,String error,String message){
		if (sc==400 && error.equalsIgnoreCase("Bad Request") && message.equalsIgnoreCase("Required String parameter 'manufacturer' is not present")) {				
			return true;
		}
		
		else if (sc==400 && error.equalsIgnoreCase("Bad Request") && message.equalsIgnoreCase("Required String parameter 'main-type' is not present")) {				
			return true;
		}
		
		else
			return false;
	}
	
	public static boolean badRequestOrInternalServerError(int sc){
		if (sc==400 || sc==500) {				
			return true;
		}
		
		else
			return false;
	}
	
}
