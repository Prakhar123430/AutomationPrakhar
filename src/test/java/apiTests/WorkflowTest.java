package apiTests;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import assertions.ValidationCaller;

/**
 * Author : Prakhar Chatterjee
 * Created on : 01/05/2021(Date Format : MM/DD/YYYY)
 * Class intent : This class contains all tests performed and the calls are made to validationCaller class.
 */

public class WorkflowTest {

	ValidationCaller validationCaller = new ValidationCaller();

	// Test to pass quer param username as "Samantha",get the user id to find out the posts and comments made by the user.
	@Test(priority=1)
	public void validateUserBlogDetails() {
		try {
			validationCaller.getUserId("username", System.getProperty("propertyName"));
			validationCaller.verifyUserPosts();
			validationCaller.addPostCommentsAndVerifyEmailFormat();

		} 
		catch(Exception e) {
			Reporter.log("Exception is" +e);
			Assert.assertTrue(false, e.getStackTrace().toString());
		}

	}

	// Test to validate Not found response when the query parameter for username api is changed to usernames that isn't valid
	@Test(priority=2)
	public void validateStatusForInvalidUserQueryParam() {
		try {
			validationCaller.checkForNotFoundUserResponseStatus();
		}

		catch(Exception e) {
			Reporter.log("Exception is" +e);
			Assert.assertTrue(false, e.getStackTrace().toString());
		}
	}

	// Test to validate empty response object when alphanumeric character is passed as a query parameter in the post api
	@Test(priority=3)
	public void validatePostApiWithAlphanumericQueryParam() {
		try {
			validationCaller.validateEmptyResponse("abc%");
		}

		catch(Exception e) {
			Reporter.log("Exception is" +e);
			Assert.assertTrue(false, e.getStackTrace().toString());
		}
	}

	// Test to validate that email key doesn't show up as anything else in the response body
	@Test(priority=4)
	public void checkEmailKeyInComments() {
		try {
			validationCaller.emailKeyValidationForCommentsSection();
		}

		catch(Exception e) {
			Reporter.log("Exception is" +e);
			Assert.assertTrue(false, e.getStackTrace().toString());
		}
	}

}
