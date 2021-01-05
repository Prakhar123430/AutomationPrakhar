package apiTests;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import bin.UserBin;
import common.EndPoints;
import common.Parameters;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import springRestServices.User;


public class UserTest {

	@Test
	public void validateUserName() {
		User user = new User();
		RequestSpecification specification = user.getUserDetailsApi();
		UserBin userBin = user.getUserById(specification,EndPoints.GET_USER_PATH_PARAM);
		int userId = userBin.getUserId();
		System.out.println(userId);
		Assert.assertEquals(userBin.getUserName(),Parameters.username);
		

	}

}
