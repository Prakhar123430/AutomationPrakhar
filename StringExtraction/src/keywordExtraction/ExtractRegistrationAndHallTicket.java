package keywordExtraction;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ExtractRegistrationAndHallTicket {

	public void initiateExtraction(String applicationRequestText){
		List<String> dataStoreList = new ArrayList<>();
		try{
			String applicationRequest[] = applicationRequestText.split("\\s+");
			int applicationRequestLength= applicationRequest.length;
			String chregex =  "^[a-zA-Z]+$";
			String regex = "^[a-zA-Z0-9]+$";
			Pattern pattern = Pattern.compile(regex);
			Pattern chpattern= Pattern.compile(chregex);


			for (int i = 0; i < applicationRequestLength; i++) {
				switch (applicationRequest[i].toLowerCase()) {
				case "registration":
					dataStoreList.add(applicationRequest[i].toLowerCase());
					break;
				case "hallticket":
					dataStoreList.add(applicationRequest[i].toLowerCase());
					break;

				case "application":
					dataStoreList.add(applicationRequest[i].toLowerCase());
					break;

				}


				if(pattern.matcher(applicationRequest[i]).matches() && !chpattern.matcher(applicationRequest[i]).matches()){
					dataStoreList.add(applicationRequest[i].toLowerCase());
				}

			}

			for(int i=0;i<dataStoreList.size();i++){
				System.out.println(dataStoreList.get(i));
			}

			dataStoreList.clear();
		}

		catch(Exception e){
			System.out.println("Exception is" +e);
		}

	}


	public static void main(String[] args) {
		Test extract= new Test();
		extract.initiateExtraction("Your application has been accepted and your registration number is 093467 and your hallticket number is BNG32016");
		//extract.initiateExtraction("Application number 9823019348 has been accepted. 0955693 is your hallticket number.");
		//extract.initiateExtraction("Hallticket is generated with number 39458 for the application AB123XZ");
	}


}
