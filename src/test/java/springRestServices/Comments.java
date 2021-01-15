package springRestServices;

import bin.CommentsBin;
import config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

/**
 * Author : Prakhar Chatterjee
 * Created on : 01/08/2021(Date Format : MM/DD/YYYY)
 * Class intent : This class is created to return the request specification and response array of objects for comment section.
 */

public class Comments {
	
	Response response;
	
	//Method to return request spec for Comments api
	public RequestSpecification getUserCommentsApi(int postId) {

		RequestSpecification requestSpecification = new RestAssuredConfig().getRequestSpecification();
		requestSpecification.queryParam("postId", postId).log().all();

		return requestSpecification;

	}
	
	//Method to return comments response as an array of objects
	public CommentsBin[] getCommentsByPostId(Response response) {
		
        CommentsBin[] commentsBin = response.as(CommentsBin[].class);

		return commentsBin;

	}

}
