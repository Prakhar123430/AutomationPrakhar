package springRestServices;

import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.testng.Assert;

import bin.CommentsBin;
import config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Comments {
	
	public RequestSpecification getUserCommentsApi(int postId) {

		RequestSpecification requestSpecification = new RestAssuredConfig().getRequestSpecification();
		requestSpecification.queryParam("postId", postId).log().all();

		return requestSpecification;

	}
	
	public CommentsBin[] getCommentsByPostId(RequestSpecification specification, String endpoint) {

	    Response response =  given().spec(specification).get(endpoint);
		Assert.assertEquals(response.getStatusCode(),HttpStatus.SC_OK);
        response.then().log().all();
        CommentsBin[] commentsBin = response.as(CommentsBin[].class);

		return commentsBin;

	}

}
