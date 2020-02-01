package keywordExtraction;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Testing {

	List<String> dataStoreList = new ArrayList<>();

	public void performExtraction(String applicationRequestText){

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

			verifyRegAndTicketNumber();
			dataStoreList.clear();
		}

		catch(Exception e){
			System.out.println("Exception is" +e);
		}


	}

	public void verifyRegAndTicketNumber(){
		try{
			if(dataStoreList.get(0).equals("application") && dataStoreList.get(1).equals("registration")){
				System.out.println(DataResource.jeeEntrance+ ":"+" " +"Registration Number is "+dataStoreList.get(2)+" "+"and Hallticket Number is "+dataStoreList.get(4));
			}

			else if(dataStoreList.get(0).equals("application") && !dataStoreList.get(1).equals("hall") && dataStoreList.get(3).equals("hall")){
				System.out.println(DataResource.vitEntrance+ ":"+" " +"Registration Number is "+dataStoreList.get(1)+" "+"and Hallticket Number is "+dataStoreList.get(2));
			}


			else if(dataStoreList.get(0).equals("application") && !dataStoreList.get(1).equals("hall") && dataStoreList.get(2).equals("hall")){
				System.out.println(DataResource.vitEntrance+ ":"+" " +"Registration Number is "+dataStoreList.get(1)+" "+"and Hallticket Number is "+dataStoreList.get(3));
			}

			else if(dataStoreList.get(0).equals("hall") && !dataStoreList.get(1).equals("application") && dataStoreList.get(3).equals("application")){
				System.out.println(DataResource.neetEntrance+ ":"+" " +"Registration Number is "+dataStoreList.get(2)+" "+"and Hallticket Number is "+dataStoreList.get(1));
			}

			else if(dataStoreList.get(0).equals("hall") && !dataStoreList.get(1).equals("application") && dataStoreList.get(2).equals("application")){
				System.out.println(DataResource.neetEntrance+ ":"+" " +"Registration Number is "+dataStoreList.get(3)+" "+"and Hallticket Number is "+dataStoreList.get(1));
			}

			else{
				System.out.println("Sorry! Your Application Is Rejected");
			}

		}

		catch(Exception e){

		}
	}




	public static void main(String[] args) {
		Testing extract= new Testing();
		extract.performExtraction(DataResource.jeeEntranceApplicationText);
		extract.performExtraction(DataResource.vitEntranceApplicationText);
		extract.performExtraction(DataResource.neetEntranceApplicatinText);
	}


}
