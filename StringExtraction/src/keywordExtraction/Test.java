package keywordExtraction;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class Test {
	public void initiateExtraction(String applicationRequestText){
		Map<String, Integer> dataStoreMap = new HashMap<>();

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
					dataStoreMap.put(applicationRequest[i].toLowerCase(), i);
					break;
				case "hallticket":
					dataStoreMap.put(applicationRequest[i].toLowerCase(), i);
					break;

				case "application":
					dataStoreMap.put(applicationRequest[i].toLowerCase(), i);
					break;

				}


				if(pattern.matcher(applicationRequest[i]).matches() && !chpattern.matcher(applicationRequest[i]).matches()){
					dataStoreMap.put(applicationRequest[i], i);
				}

			}
			
			System.out.println(dataStoreMap);

			/*Set<String> keySet = dataStoreMap.keySet();
			Iterator<String> it = keySet.iterator();
			while(it.hasNext()){
				System.out.println(it.next());
			}
*/

		}

		catch(Exception e){
			System.out.println("Exception is" +e);
		}

	}

	public static void main(String[] args) {
		Test extract= new Test();
		extract.initiateExtraction("Your application has been accepted and your registration number is 093467 and your hallticket number is BNG32016");
		extract.initiateExtraction("Application number 9823019348 has been accepted. 0955693 is your hallticket number.");
		extract.initiateExtraction("Hallticket is generated with number 39458 for the application AB123XZ");
	}

}
