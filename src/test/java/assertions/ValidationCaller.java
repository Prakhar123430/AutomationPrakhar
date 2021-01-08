package assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.testng.Assert;
import org.testng.Reporter;

import com.codoid.products.exception.FilloException;
import bin.CommentsBin;
import bin.PostBin;
import bin.UserBin;
import common.EndPoints;
import io.restassured.specification.RequestSpecification;
import springRestServices.Comments;
import springRestServices.Post;
import springRestServices.User;

public class ValidationCaller {

	RequestSpecification specification;
	int userId;
	List<Integer> postId = new ArrayList<Integer>();

	public void verifyUserName() {
		User user = new User();
		specification = user.getUserDetailsApi();
		UserBin userBin = user.getUserById(specification,EndPoints.GET_USER);
		userId = userBin.getUserId();
		Assert.assertEquals(userBin.getUserName(),System.getProperty("propertyName"));

	}

	public void verifyUserPosts() throws FilloException {
		Post post = new Post();
		List<String> postBodies = new ArrayList<String>();
		List<String> postTitles = new ArrayList<String>();
		List<String> postBodiesNew = post.storePostBodies();
		List<String> postTitlesNew = post.storePostTitles();

		specification = post.getUserPostsApi(userId);
		PostBin[] postBin = post.getPostsByUserId(specification,EndPoints.GET_POSTS);
		for(PostBin posts : postBin) {
			postId.add(posts.getId());
			String postBody = posts.getBody();
			postBodies.add(postBody);
			String postTitle = posts.getTitle();
			postTitles.add(postTitle);
		}


		for(int i=0; i<postBodies.size(); i++) {
			Assert.assertEquals(postBodies.get(i).trim(), postBodiesNew.get(i).trim());
			Assert.assertEquals(postTitles.get(i).trim(), postTitlesNew.get(i).trim());
		}

	}

	public void addPostCommentsAndVerifyEmailFormat() {
		Comments comments = new Comments();
		List<String> commentBody = new ArrayList<String>();
		List<String> commentName = new ArrayList<String>();
		for(int i=0; i<postId.size(); i++) {
			specification = comments.getUserCommentsApi(postId.get(i));
			CommentsBin[] commentsBin = comments.getCommentsByPostId(specification, EndPoints.GET_COMMENTS);
			for(CommentsBin comment : commentsBin) {
				commentBody.add(comment.getBody());
				commentName.add(comment.getName());
				boolean isMatching = validateEmailFormat(comment.getEmail());
				verifyIfCorrectEmail(isMatching,comment.getEmail());

			}
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

}
