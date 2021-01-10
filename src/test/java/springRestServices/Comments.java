package springRestServices;

import bin.CommentsBin;
import config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Comments {
	
	Response response;
	
	public RequestSpecification getUserCommentsApi(int postId) {

		RequestSpecification requestSpecification = new RestAssuredConfig().getRequestSpecification();
		requestSpecification.queryParam("postId", postId).log().all();

		return requestSpecification;

	}
	
	public CommentsBin[] getCommentsByPostId(Response response) {
		
        CommentsBin[] commentsBin = response.as(CommentsBin[].class);

		return commentsBin;

	}

}
