package springRestServices;

import java.util.List;
import com.codoid.products.exception.FilloException;
import bin.PostBin;
import common.Utility;
import config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Author : Prakhar Chatterjee
 * Created on : 01/07/2021(Date Format : MM/DD/YYYY)
 * Class intent : This class is created to return the request specification and response array of objects for post section.
 */

public class Post {

	Response response;

	//Method to return request spec for Posts api
	public RequestSpecification getUserPostsApi(int userId) {

		RequestSpecification requestSpecification = new RestAssuredConfig().getRequestSpecification();
		requestSpecification.queryParam("userId", userId).log().all();

		return requestSpecification;

	}
	
	//Method to return posts response as an array of objects
	public RequestSpecification getUserPostsApi(String userId) {

		RequestSpecification requestSpecification = new RestAssuredConfig().getRequestSpecification();
		requestSpecification.queryParam("userId", userId).log().all();

		return requestSpecification;

	}

	public PostBin[] getPostsByUserId(Response response) {
		PostBin[] postBin = response.as(PostBin[].class);

		return postBin;

	}

	public List<String> storePostBodies() throws FilloException {
		Utility utility = new Utility();
		List<String> bodies =  utility.readPostBody();

		return bodies;
	}

	public List<String> storePostTitles() throws FilloException {
		Utility utility = new Utility();
		List<String> titles =  utility.readPostTitle();

		return titles;
	}
}
