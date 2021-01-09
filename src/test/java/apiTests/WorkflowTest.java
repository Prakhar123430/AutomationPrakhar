package apiTests;


import org.testng.annotations.Test;
import com.codoid.products.exception.FilloException;
import assertions.ValidationCaller;


public class WorkflowTest {

	ValidationCaller validationCaller = new ValidationCaller();

	@Test(priority=1)
	public void validateUserBlogDetails() throws FilloException{
		validationCaller.verifyUserName();
		validationCaller.verifyUserPosts();
		validationCaller.addPostCommentsAndVerifyEmailFormat();

	}

}
