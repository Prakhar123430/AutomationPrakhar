package springRestServices;

import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.testng.Assert;

import bin.UserBin;
import common.EndPoints;
import common.Parameters;
import config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class User {

	public RequestSpecification getUserDetailsApi() {

		RequestSpecification requestSpecification = new RestAssuredConfig().getRequestSpecification();
		requestSpecification.pathParam("userId", Parameters.samanthaPathParam).log().all();

		return requestSpecification;

	}

	public UserBin getUserById(RequestSpecification specification, String endpoint) {

		Response response = given().spec(specification).get(endpoint);
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);
        response.then().log().all();
        UserBin userBin = response.as(UserBin.class);

		return userBin;

	}



}
