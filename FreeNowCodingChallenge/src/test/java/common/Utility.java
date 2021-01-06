package common;

import java.util.ArrayList;
import java.util.List;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class Utility {
	
	public List<String> readPostBody() throws FilloException{
		 List<String> postBodies = new ArrayList<String>();
		 Fillo fillo = new Fillo();
		 Connection connection = fillo.getConnection("./src/test/resources/PostTitleAndBody/FreeNow.xlsx");
		 String strQuery = "select * from Posts";
		 Recordset recordSet = connection.executeQuery(strQuery);
		 
		 while(recordSet.next()) {
			 postBodies.add(recordSet.getField("Body").replace("\n", " "));
			 
		 }
		 
		 recordSet.close();
		 connection.close();
		 
		 return postBodies;
		
	}

}
