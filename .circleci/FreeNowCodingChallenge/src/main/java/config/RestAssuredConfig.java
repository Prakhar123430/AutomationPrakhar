package config;
import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestAssuredConfig {

	@BeforeSuite(alwaysRun = true)
	public void configure() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
	}

	public RequestSpecification getRequestSpecification() {
		return RestAssured.given().contentType(ContentType.JSON);
	}


}
