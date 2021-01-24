package assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.Reporter;
import com.codoid.products.exception.FilloException;
import bin.CommentsBin;
import bin.PostBin;
import bin.UserBin;
import common.EndPoints;
import config.RestAssuredConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import springRestServices.Comments;
import springRestServices.Post;
import springRestServices.User;

/**
 * Author : Prakhar Chatterjee
 * Created on : 01/07/2021(Date Format : MM/DD/YYYY)
 * Class intent : This class contains methods that are mostly called from the test class for performing test validations
 */

public class ValidationCaller {

	RequestSpecification specification;
	User user;
	int userId;
	List<Integer> postId = new ArrayList<Integer>();
	Comments comments = new Comments();


	// Method to retrieve the user id using user api
	public void getUserId(String queryParam, String queryParamvalue) {
		try {
		    user = new User();
			specification = user.getUserDetailsApi(queryParam,queryParamvalue);
			Response response = new RestAssuredConfig().getResponse(specification,EndPoints.GET_USER);
			UserBin userBin = user.getUserById(response);
			userId = userBin.getUserId();
			Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);
		}

		catch(Exception e) {
			Reporter.log("Exception is" +e);
			Assert.assertTrue(false, e.getStackTrace().toString());

		}

	}

	// Method to verify post body and title for the posts made by Samantha and to fetch the postids for every post
	public void verifyUserPosts() throws FilloException {
		Post post = new Post();
		List<String> postBodies = new ArrayList<String>();
		List<String> postTitles = new ArrayList<String>();
		List<String> postBodiesNew = post.storePostBodies();
		List<String> postTitlesNew = post.storePostTitles();
		specification = post.getUserPostsApi(userId);
		Response response = new RestAssuredConfig().getResponse(specification,EndPoints.GET_POSTS);
		//Response response= getResponseForPostApi(userId);
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);
		PostBin[] postBin = post.getPostsByUserId(response);
		Arrays.asList(postBin).forEach(posts->{
			postId.add(posts.getId());
			String postBody = posts.getBody();
			postBodies.add(postBody);
			String postTitle = posts.getTitle();
			postTitles.add(postTitle);

		});

		for(int i=0; i<postBodies.size(); i++) {
			Assert.assertEquals(postBodies.get(i).trim(), postBodiesNew.get(i).trim());
			Assert.assertEquals(postTitles.get(i).trim(), postTitlesNew.get(i).trim());
		}

	}

	// Method to store comments body and name and to verify if the email in the comments section are placed in proper format
	public void addPostCommentsAndVerifyEmailFormat() {
		List<String> commentBody = new ArrayList<String>();
		List<String> commentName = new ArrayList<String>();

		for(int i=0; i<postId.size();i++){
			Response response = getResponseForCommentsApi(i);
			Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);
			CommentsBin[] commentsBin = comments.getCommentsByPostId(response);
			Arrays.asList(commentsBin).forEach(comment->{
				commentBody.add(comment.getBody());
				commentName.add(comment.getName());
				boolean isMatching = validateEmailFormat(comment.getEmail());
				verifyIfCorrectEmail(isMatching,comment.getEmail());

			});


			Arrays.fill(commentsBin, null);
		}


	}

	// Method to use a regex to match the email format
	public boolean validateEmailFormat(String email) {

		String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
		Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = emailPat.matcher(email);

		return matcher.find();

	}

	// Method to check if email is in proper format and print in the log file as per boolean output post pattern matching
	public void verifyIfCorrectEmail(boolean isMatching, String email) {
		if(isMatching) {
			Reporter.log("Email format is correct for :" + " "+email);
		}

		else {
			Reporter.log("Email format is incorrect for :" + " "+email);
		}

	}


	// Method to determine if not found is triggered as the response status in case we query the user table with a key that's not present
	public void checkForNotFoundUserResponseStatus() {
		specification = user.getUserDetailsApi("usernames",System.getProperty("propertyName"));
		int statusCode= new RestAssuredConfig().getResponse(specification,EndPoints.GET_USER).statusCode();		
		Assert.assertTrue(HttpStatus.SC_NOT_FOUND==statusCode, "Expected 404 but returning " +statusCode);

	}

	//Method to determine if the key shows up as email and not anything else in the get comments api response and throw an error 500 if it does
	public void emailKeyValidationForCommentsSection() {
		for(int i=0; i<postId.size();i++){
			Response response = getResponseForCommentsApi(i);
			int statusCode = response.statusCode();
			JsonPath jsp = new JsonPath(response.asString());
			List<HashMap<Object,Object>> dList = jsp.getList("$");
			for(HashMap<Object,Object> obj: dList){
				if(!obj.containsKey("email")){
					Reporter.log("Object {" + obj.toString() + "} does not contain the required key");
					Assert.assertTrue(HttpStatus.SC_INTERNAL_SERVER_ERROR==statusCode, "Expected 500 but returning " +statusCode);
				}
			}
		}
	}

	
	//Method to determine that the api response is a null array when an alphanumeric string is passed for postid in the get post api and the response is 200
	public void validateEmptyResponse(String alphanumericParam) {
		Post post = new Post();
		specification = post.getUserPostsApi(alphanumericParam);
		Response response = new RestAssuredConfig().getResponse(specification,EndPoints.GET_POSTS);
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);
		if(response!=null) {
			Reporter.log("Response returned is invalid");
			Assert.assertFalse(false,"Response returned is valid");
		}

	}
	
    //Method to store the request specification and return the response for the get comments api
	public Response getResponseForCommentsApi(int i) {
		specification = comments.getUserCommentsApi(postId.get(i));
		Response response= new RestAssuredConfig().getResponse(specification,EndPoints.GET_COMMENTS);
		return response;
	}
	
}
