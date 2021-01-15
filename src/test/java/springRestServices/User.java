package springRestServices;

import bin.UserBin;
import config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Author : Prakhar Chatterjee
 * Created on : 01/05/2021(Date Format : MM/DD/YYYY)
 * Class intent : This class is created to return the request specification and response array of objects for user section.
 */

public class User {

	Response response;

	//Method to return request spec for User api
	public RequestSpecification getUserDetailsApi(String queryParam, String queryParamValue) {

		RequestSpecification requestSpecification = new RestAssuredConfig().getRequestSpecification();
		requestSpecification.queryParam(queryParam, queryParamValue).log().all();

		return requestSpecification;

	}


	//Method to return user response as an array of objects
	public UserBin getUserById(Response response) {
		UserBin[] userBin = response.as(UserBin[].class);

		return userBin[0];


	}

}
