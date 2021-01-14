package apiTests;


import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import assertions.ValidationCaller;


public class WorkflowTest {

	ValidationCaller validationCaller = new ValidationCaller();

	@Test(priority=1)
	public void validateUserBlogDetails() {
		try {
			validationCaller.verifyUserName("username", System.getProperty("propertyName"));
			validationCaller.verifyUserPosts();
			validationCaller.addPostCommentsAndVerifyEmailFormat();

		} 
		catch(Exception e) {
			Reporter.log("Exception is" +e);
			Assert.assertTrue(false, e.getStackTrace().toString());
		}

	}


	@Test(priority=2)
	public void validateStatusForInvalidUserQueryParam() {
		try {
			validationCaller.checkForNullUserResponseStatus();
		}

		catch(Exception e) {
			Reporter.log("Exception is" +e);
			Assert.assertTrue(false, e.getStackTrace().toString());
		}
	}
	
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
