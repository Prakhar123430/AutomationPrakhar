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
			validationCaller.userResponseStatus();
		}

		catch(Exception e) {
			Reporter.log("Exception is" +e);
			Assert.assertTrue(false, e.getStackTrace().toString());
		}
	}


}
