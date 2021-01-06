package apiTests;

import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;

import bin.PostBin;
import bin.UserBin;
import common.EndPoints;
import common.Parameters;
import io.restassured.specification.RequestSpecification;
import springRestServices.Post;
import springRestServices.User;


public class WorkflowTest {

RequestSpecification specification;
int userId;

	@Test(priority=1)
	public void validateUserName() {
		User user = new User();
		specification = user.getUserDetailsApi();
		UserBin userBin = user.getUserById(specification,EndPoints.GET_USER);
		userId = userBin.getUserId();
		Assert.assertEquals(userBin.getUserName(),Parameters.username);
		

	}
	
	@Test(priority=2)
	public void validateUserPosts() throws FilloException {
		Post post = new Post();
		List<String> postBodies = new ArrayList<String>();
		specification = post.getUserPostsApi(userId);
		PostBin[] postBin = post.getPostsByUserId(specification,EndPoints.GET_POSTS);
		for(PostBin posts : postBin) {
			String postBody = posts.getBody();
			postBodies.add(postBody);
		}
		
		List<String> postBodiesNew = post.storePostBodies();
		
		for(int i=0; i<postBodies.size(); i++) {
			postBodiesNew.get(i).trim();
			String reformedBody = postBodies.get(i).trim().replaceAll("[\\t\\n\\r]+"," ");
			Assert.assertEquals(reformedBody, postBodiesNew.get(i));
		}
		
	}

}
