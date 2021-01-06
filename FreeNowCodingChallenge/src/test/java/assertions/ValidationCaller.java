package assertions;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.codoid.products.exception.FilloException;

import bin.PostBin;
import bin.UserBin;
import common.EndPoints;
import io.restassured.specification.RequestSpecification;
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

}
