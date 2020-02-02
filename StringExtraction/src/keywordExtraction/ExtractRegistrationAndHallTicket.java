package keywordExtraction;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ExtractRegistrationAndHallTicket {

	List<String> dataStoreList = new ArrayList<>();

	public void performExtraction(String applicationRequestText, String entranceExamName){

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
				case "hall":
					dataStoreList.add(applicationRequest[i].toLowerCase());
					break;

				case "application":
					dataStoreList.add(applicationRequest[i].toLowerCase());
					break;

				}


				if(pattern.matcher(applicationRequest[i]).matches() && !chpattern.matcher(applicationRequest[i]).matches()){
					dataStoreList.add(applicationRequest[i]);
				}

			}

			displayRegistrationAndHallTicketNumbers(entranceExamName);
			dataStoreList.clear();
		}

		catch(Exception e){
			System.out.println("Exception is" +e);
		}


	}


	public void displayRegistrationAndHallTicketNumbers(String entranceExamName){

		try{
			if(dataStoreList.get(0).equals("application") && dataStoreList.get(1).equals("registration") && dataStoreList.get(3).equals("hall")){
				System.out.println(entranceExamName+ ":"+" " +"Registration Number is "+dataStoreList.get(2)+" "+"and Hallticket Number is "+dataStoreList.get(4));
			}

			else if(dataStoreList.get(0).equals("application") && dataStoreList.get(1).equals("hall") && dataStoreList.get(3).equals("registration")){
				System.out.println(entranceExamName+ ":"+" " +"Registration Number is "+dataStoreList.get(4)+" "+"and Hallticket Number is "+dataStoreList.get(2));
			}

			else if(dataStoreList.get(0).equals("registration") && dataStoreList.get(1).equals("application") && dataStoreList.get(3).equals("hall")){
				System.out.println(entranceExamName+ ":"+" " +"Registration Number is "+dataStoreList.get(2)+" "+"and Hallticket Number is "+dataStoreList.get(4));
			}

			else if(dataStoreList.get(0).equals("registration") && dataStoreList.get(1).equals("hall") && dataStoreList.get(2).equals("application")){
				System.out.println(entranceExamName+ ":"+" " +"Registration Number is "+dataStoreList.get(3)+" "+"and Hallticket Number is "+dataStoreList.get(4));
			}

			else if(dataStoreList.get(0).equals("application") && dataStoreList.get(1).equals("registration") && dataStoreList.get(2).equals("hall")){
				System.out.println(entranceExamName+ ":"+" " +"Registration Number is "+dataStoreList.get(3)+" "+"and Hallticket Number is "+dataStoreList.get(4));
			}

			else if(dataStoreList.get(0).equals("application") && dataStoreList.get(1).equals("hall") && dataStoreList.get(2).equals("registration")){
				System.out.println(entranceExamName+ ":"+" " +"Registration Number is "+dataStoreList.get(4)+" "+"and Hallticket Number is "+dataStoreList.get(3));
			}

			else if(dataStoreList.get(0).equals("registration") && dataStoreList.get(1).equals("hall") && !dataStoreList.get(2).equals("application")){
				System.out.println(entranceExamName+ ":"+" " +"Registration Number is "+dataStoreList.get(2)+" "+"and Hallticket Number is "+dataStoreList.get(3));
			}
			else if(dataStoreList.get(0).equals("hall") && dataStoreList.get(1).equals("registration") && !dataStoreList.get(2).equals("application")){
				System.out.println(entranceExamName+ ":"+" " +"Registration Number is "+dataStoreList.get(3)+" "+"and Hallticket Number is "+dataStoreList.get(2));
			}

			else if(dataStoreList.get(0).equals("registration") && dataStoreList.get(2).equals("hall")){
				System.out.println(entranceExamName+ ":"+" " +"Registration Number is "+dataStoreList.get(1)+" "+"and Hallticket Number is "+dataStoreList.get(3));
			}

			else if(dataStoreList.get(0).equals("hall") && dataStoreList.get(2).equals("registration")){
				System.out.println(entranceExamName+ ":"+" " +"Registration Number is "+dataStoreList.get(3)+" "+"and Hallticket Number is "+dataStoreList.get(1));
			}

			else if(dataStoreList.get(0).equals("registration") && dataStoreList.get(3).equals("hall")){
				System.out.println(entranceExamName+ ":"+" " +"Registration Number is "+dataStoreList.get(1)+" "+"and Hallticket Number is "+dataStoreList.get(2));
			}

			else if(dataStoreList.get(0).equals("hall") && dataStoreList.get(3).equals("registration")){
				System.out.println(entranceExamName+ ":"+" " +"Registration Number is "+dataStoreList.get(2)+" "+"and Hallticket Number is "+dataStoreList.get(1));
			}
			
			else if(dataStoreList.get(0).equals("application") && dataStoreList.get(3).equals("hall")){
				System.out.println(entranceExamName+ ":"+" " +"Registration Number is "+dataStoreList.get(1)+" "+"and Hallticket Number is "+dataStoreList.get(2));
			}
			
			else if(dataStoreList.get(0).equals("hall") && dataStoreList.get(3).equals("application")){
				System.out.println(entranceExamName+ ":"+" " +"Registration Number is "+dataStoreList.get(2)+" "+"and Hallticket Number is "+dataStoreList.get(1));
			}
			
			else if(dataStoreList.get(0).equals("application") && dataStoreList.get(2).equals("hall")){
				System.out.println(entranceExamName+ ":"+" " +"Registration Number is "+dataStoreList.get(1)+" "+"and Hallticket Number is "+dataStoreList.get(3));
			}
			
			else if(dataStoreList.get(0).equals("hall") && dataStoreList.get(2).equals("application")){
				System.out.println(entranceExamName+ ":"+" " +"Registration Number is "+dataStoreList.get(3)+" "+"and Hallticket Number is "+dataStoreList.get(1));
			}
			

			else if(dataStoreList.get(0).equals("registration") && dataStoreList.get(1).equals("hall")){
				System.out.println(entranceExamName+ ":"+" " +"Registration Number is "+dataStoreList.get(2)+" "+"and Hallticket Number is "+dataStoreList.get(3));
			}
			
			else if(dataStoreList.get(0).equals("hall") && dataStoreList.get(1).equals("registration")){
				System.out.println(entranceExamName+ ":"+" " +"Registration Number is "+dataStoreList.get(3)+" "+"and Hallticket Number is "+dataStoreList.get(2));
			}
			
			else{
				System.out.println("Sorry! Your application has been rejected");
			}
		}

		catch(Exception e){
			System.out.println("Exception is" +e);
		}
	}




	public static void main(String[] args) {
		ExtractRegistrationAndHallTicket extract= new ExtractRegistrationAndHallTicket();
		extract.performExtraction(DataResource.jeeEntranceApplicationText,DataResource.jeeEntrance);
		extract.performExtraction(DataResource.vitEntranceApplicationText,DataResource.vitEntrance);
		extract.performExtraction(DataResource.neetEntranceApplicatinText,DataResource.neetEntrance);
	}


}
