package assertions;

import java.util.ArrayList;
import java.util.Arrays;
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
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import springRestServices.Comments;
import springRestServices.Post;
import springRestServices.User;

public class ValidationCaller {

	RequestSpecification specification;
	User user;
	int userId;
	List<Integer> postId = new ArrayList<Integer>();


	public void verifyUserName(String queryParam, String queryParamvalue) {
		user = new User();
		specification = user.getUserDetailsApi(queryParam,queryParamvalue);
		Response response = new RestAssuredConfig().getResponse(specification,EndPoints.GET_USER);
		UserBin userBin = user.getUserById(response);
		userId = userBin.getUserId();
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK);
	}

	public void verifyUserPosts() throws FilloException {
		Post post = new Post();
		List<String> postBodies = new ArrayList<String>();
		List<String> postTitles = new ArrayList<String>();
		List<String> postBodiesNew = post.storePostBodies();
		List<String> postTitlesNew = post.storePostTitles();
		specification = post.getUserPostsApi(userId);
		Response response = new RestAssuredConfig().getResponse(specification,EndPoints.GET_POSTS);
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

	public void addPostCommentsAndVerifyEmailFormat() {
		Comments comments = new Comments();
		List<String> commentBody = new ArrayList<String>();
		List<String> commentName = new ArrayList<String>();

		for(int i=0; i<postId.size();i++){
			specification = comments.getUserCommentsApi(postId.get(i));
			Response response = new RestAssuredConfig().getResponse(specification,EndPoints.GET_COMMENTS);
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

	public boolean validateEmailFormat(String email) {

		String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
		Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = emailPat.matcher(email);

		return matcher.find();

	}

	public void verifyIfCorrectEmail(boolean isMatching, String email) {
		if(isMatching) {
			Reporter.log("Email format is correct for :" + " "+email);
		}

		else {
			Reporter.log("Email format is incorrect for :" + " "+email);
		}

	}

	public void userResponseStatus() {
		specification = user.getUserDetailsApi("usernames",System.getProperty("propertyName"));
		int statusCode = new RestAssuredConfig().getResponse(specification,EndPoints.GET_USER).statusCode();		
		Assert.assertTrue(HttpStatus.SC_INTERNAL_SERVER_ERROR==statusCode, "Expected 500 but returning " +statusCode);

	}

}
