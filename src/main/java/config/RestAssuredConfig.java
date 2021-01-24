package config;

import org.testng.annotations.BeforeSuite;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Author : Prakhar Chatterjee
 * Created on : 01/05/2021(Date Format : MM/DD/YYYY)
 * Class intent : This class contains the rest assured configurations.
 */

public class RestAssuredConfig {

	@BeforeSuite(alwaysRun = true)
	public void configure() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
	}

	public RequestSpecification getRequestSpecification() {
		return RestAssured.given().contentType(ContentType.JSON);
	}

	public Response getResponse(RequestSpecification requestSpecification,String endpoint){
		Response response = requestSpecification.get(endpoint);
		response.then().log().all();
		return response;
	}
}
