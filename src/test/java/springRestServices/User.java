package springRestServices;

import bin.UserBin;
import config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class User {

	Response response;


	public RequestSpecification getUserDetailsApi(String queryParam, String queryParamValue) {

		RequestSpecification requestSpecification = new RestAssuredConfig().getRequestSpecification();
		requestSpecification.queryParam(queryParam, queryParamValue).log().all();

		return requestSpecification;

	}



	public UserBin getUserById(Response response) {
		UserBin[] userBin = response.as(UserBin[].class);

		return userBin[0];


	}

}
