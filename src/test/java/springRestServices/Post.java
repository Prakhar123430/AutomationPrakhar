package springRestServices;

import java.util.List;
import com.codoid.products.exception.FilloException;
import bin.PostBin;
import common.Utility;
import config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Post {

	Response response;

	public RequestSpecification getUserPostsApi(int userId) {

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
