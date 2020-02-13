package moviePages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import commonUtilities.ReusableData;
import commonUtilities.ReusableMethods;


public class MovieNamesPage {

	@FindBy(how = How.XPATH, using = ".//label[@id='imdbHeader-navDrawerOpen--desktop']")
	WebElement menuLink;


	@FindBy(how = How.XPATH, using = ".//span[contains(text(),'Top Rated Movies')]/..")
	WebElement topRatedMovieLink;

	@FindBy(how=How.XPATH, using = "//h1[text()='Top Rated Movies']")
	WebElement topMoviesHeaderText;

	public WebDriver driver;
	ReusableMethods reusableMethods = new ReusableMethods();
	List<String> movieNames = new ArrayList<String>();
	List<String> movieYears = new ArrayList<String>();
	List<String> movieRatings = new ArrayList<String>();

	public MovieNamesPage(WebDriver driver) {
		try {
			this.driver=driver;
			PageFactory.initElements(new AjaxElementLocatorFactory(driver, 30), this);
		}

		catch(Exception e) {
			Logger.getLogger("Exception is" +e);
		}
	}

	public void navigateToTopRatedMovieList(WebDriver driver){

		try{
			reusableMethods.clickElement(driver, menuLink);
			reusableMethods.explicitWait(driver, topRatedMovieLink);
			reusableMethods.clickElement(driver, topRatedMovieLink);
		}

		catch(Exception e){
			Logger.getLogger("Exception is" +e);

		}

	}

	public void checkIfLandedToTopRatedMoviePage(){

		try{
			reusableMethods.explicitWait(driver, topMoviesHeaderText);
			reusableMethods.verifyIfPresent(driver, topMoviesHeaderText);
		}

		catch(Exception e){
			Logger.getLogger("Exception is" +e);
		}
	}

	public void fetchAllTopMoviesInAList(WebDriver driver){


		try{
			WebElement table= driver.findElement(By.xpath(ReusableData.movieDetailsXpath));
			List<WebElement> column = table.findElements(By.tagName("td"));
			for(int i=1;i<column.size();i++){
				if(NumberUtils.isCreatable(column.get(i).getText())){
					storeMovieRatings(column.get(i).getText());
				}
				else{
					storeMovieNamesYearAndRatingsInAList(column.get(i).getText());
				}
			}

			for(int i=0;i<movieNames.size();i++){
				System.out.println(i + "."+ " " +movieNames.get(i) + " " + movieYears.get(i) + " " + movieRatings.get(i));
			}

		}

		catch(Exception e){
			Logger.getLogger("Exception is" +e);
		}

	}

	public void storeMovieRatings(String movieRatingString){

		try{
			movieRatings.add(movieRatingString);
		}

		catch(Exception e){
			Logger.getLogger("Exception is" +e);
		}
	}

	public void storeMovieNamesYearAndRatingsInAList(String movieDetailString){

		String movieName = "";
		String movieYear = "";
		try{
			if(!movieDetailString.equals("")){
				String[] movieDetail = movieDetailString.split("\\s+");
				for(int i=0;i<movieDetail.length-1;i++){
					if(!NumberUtils.isCreatable(movieDetail[i])){
						movieName = movieName + movieDetail[i] + " ";
					}
				}

				movieYear= movieDetail[movieDetail.length-1];
				movieYear=movieYear.substring(1,5);
				movieNames.add(movieName);
				movieYears.add(movieYear);
			}

		}

		catch(Exception e){
			Logger.getLogger("Exception is" +e);
		}
	}
}
