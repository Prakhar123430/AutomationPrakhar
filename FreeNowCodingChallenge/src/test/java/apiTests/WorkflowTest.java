package apiTests;


import org.testng.annotations.Test;
import com.codoid.products.exception.FilloException;
import assertions.ValidationCaller;


public class WorkflowTest {

	ValidationCaller validationCaller = new ValidationCaller();

	@Test(priority=1)
	public void validateUserName() {
		validationCaller.verifyUserName();

	}

	@Test(priority=2)
	public void validateUserPosts() throws FilloException {
		validationCaller.verifyUserPosts();

	}

}
