package commonUtilities;

public interface ReusableData {
	
	public String imdbURL= "https://www.imdb.com/";
	public String movieDetailsXpath= "//*[contains(@data-caller-name,'top250movie')]";
	public String movieDetailFilePath= "./src/test/resources/MovieDetails.csv";
}
