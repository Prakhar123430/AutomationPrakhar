package movieStepDefinition;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import commonUtilities.ReusableData;
import commonUtilities.ReusableMethods;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import moviePages.MovieNamesPage;

public class DisplayTop250MovieDetails {

	public WebDriver driver;
	ReusableMethods reusableMethods = new ReusableMethods();
	MovieNamesPage movieNamePages;

	@Before
	public void setUpConfiguration() throws Exception{
		driver = reusableMethods.getDriver();
		movieNamePages = new MovieNamesPage(driver);
	}

	@Test
	@Given("^user navigates to IMDB website$")
	public void user_navigates_to_IMDB_website() throws Exception {
		driver.get(ReusableData.imdbURL);
		driver.manage().window().maximize();
	}

	@When("^user navigates to Top Rated movies from the menu list$")
	public void user_navigates_to_Top_Rated_movies_from_the_menu_list() throws Exception {
		movieNamePages.navigateToTopRatedMovieList(driver);
       
	}

	@When("^user verifies that he has landed to the correct page$")
	public void user_verifies_that_he_has_landed_to_the_correct_page() throws Exception {
		movieNamePages.checkIfLandedToTopRatedMoviePage();
	}

	@Then("^user displays the top movie names in chronological sequence$")
	public void user_displays_the_top_movies_in_chronological_sequence() throws Exception {
		movieNamePages.fetchAllTopMoviesInAList(driver);
}

	@After
	public void tearDown() throws Exception{
		driver.quit();
	}

}
