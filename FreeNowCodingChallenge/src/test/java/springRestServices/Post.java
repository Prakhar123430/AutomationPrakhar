package springRestServices;

import static io.restassured.RestAssured.given;
import java.util.List;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import com.codoid.products.exception.FilloException;
import bin.PostBin;
import common.Utility;
import config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Post {
	
	public RequestSpecification getUserPostsApi(int userId) {

		RequestSpecification requestSpecification = new RestAssuredConfig().getRequestSpecification();
		requestSpecification.queryParam("userId", userId).log().all();

		return requestSpecification;

	}

	public PostBin[] getPostsByUserId(RequestSpecification specification, String endpoint) {

	    Response response =  given().spec(specification).get(endpoint);
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);
        response.then().log().all();
        PostBin[] postBin = response.as(PostBin[].class);

		return postBin;

	}
	
	public List<String> storePostBodies() throws FilloException {
		Utility utility = new Utility();
		List<String> bodies =  utility.readPostBody();
		
		return bodies;
	}
}
