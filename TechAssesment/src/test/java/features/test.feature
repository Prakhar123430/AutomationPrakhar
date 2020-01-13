Feature: Get Manufacturer Details

Scenario: Successful selection of manufacturer details with all parameters
      Given user sets the GET baseURL 
      When user appends the query parameters
        | coding-puzzle-client-449cc9d | en | 1 |
	  And sends a GET http request
	  Then user validates the response
	      
      
Scenario: Selection of manufacturer details without Key
      Given user sets the GET baseURL 
      When user appends the query parameters 
      | coding-puzzle-client-449cc9d | en | 3 |
	  And sends a GET http request
	  Then user validates the response is unauthorized
      
Scenario: Selection of manufacturer details without Locale
      Given user sets the GET baseURL 
      When user appends the query parameters 
      | coding-puzzle-client-449cc9d | en | 2 |
	  And sends a GET http request
	  Then user validates the response is a bad request or internal server error
	  
Scenario: Checking main type dropdown without manufacturer code
      Given user sets the GET baseURL 
      When user appends the query parameters for maintype
      | coding-puzzle-client-449cc9d | en | 020 | 2 |
	  And sends a GET http request
	  Then user validates that the response is a bad request or internal server error
	  
Scenario: Selection of maintype details without Locale
      Given user sets the GET baseURL 
      When user appends the query parameters for maintype
       | coding-puzzle-client-449cc9d | en | 020 | 4 |
	  And sends a GET http request
	  Then user validates the response is a bad request or internal server error
	  
Scenario: Selection of main type details without Key
      Given user sets the GET baseURL 
      When user appends the query parameters 
      | coding-puzzle-client-449cc9d | en | 020 | 3 |
	  And sends a GET http request
	  Then user validates the response is unauthorized
     
Scenario: Enter two digit manufacturer code to check the main type response
      Given user sets the GET baseURL 
      When user appends the query parameters 
      | coding-puzzle-client-449cc9d | en | 20 | 1 |
	  And sends a GET http request
	  Then user validates the response is a bad request or internal server error
	  
Scenario: Enter arbitrary three digit manufacturer code to check the main type response
      Given user sets the GET baseURL 
      When user appends the query parameters 
      | coding-puzzle-client-449cc9d | en | 123 | 1 |
	  And sends a GET http request
	  Then user validates the response is a bad request or internal server error
	  
Scenario: Selection of built in details without Key
      Given user sets the GET baseURL 
      When user appends the query parameters for built in
      | coding-puzzle-client-449cc9d | en | 020 | 500D | 3 |
	  And sends a GET http request
	  Then user validates the response is unauthorized
	  
	  	  
Scenario: Selection of built in details without Locale
      Given user sets the GET baseURL 
      When user appends the query parameters for built in
       | coding-puzzle-client-449cc9d | en | 020 | 500D | 4 |
	  And sends a GET http request
	  Then user validates the response is a bad request or internal server error
	  
Scenario: Checking built in dropdown without manufacturer code
      Given user sets the GET baseURL 
      When user appends the query parameters for built in
      | coding-puzzle-client-449cc9d | en | 020 | 500D | 2 |
	  And sends a GET http request
	  Then user validates that the response is a bad request or internal server error
	  
Scenario: Checking built in dropdown without main type
      Given user sets the GET baseURL 
      When user appends the query parameters for built in
      | coding-puzzle-client-449cc9d | en | 020 | 500D | 5 |
	  And sends a GET http request
	  Then user validates that the response is a bad request or internal server error

Scenario: Enter two digit main type code to check the built in  response
      Given user sets the GET baseURL 
      When user appends the query parameters for built in
      | coding-puzzle-client-449cc9d | en | 020 | 50 | 1 |
	  And sends a GET http request
	  Then user validates the response is a bad request or internal server error
	  
Scenario: Checking built in dropdown without manufacturer code
      Given user sets the GET baseURL 
      When user appends the query parameters for built in
      | coding-puzzle-client-449cc9d | en | 020 | 500D | 6 |
	  And sends a GET http request
	  Then user validates that the response is a bad request or internal server error
	  
Scenario: Selection for main type for every manufacturer code
      Given user sets the GET baseURL
      When user appends the query parameters 
      | coding-puzzle-client-449cc9d | en | 1 | 
      And sends a GET http request
      And user fetches a list of manufacture codes from manufacture API
      And user sends a GET http request using the manufacturer code
      | coding-puzzle-client-449cc9d | en | 1 |
      Then user validates response of main type for each manufacturer code
      And user sends a GET http request using the manufacture code and main type codes
      | coding-puzzle-client-449cc9d | en | 1 |
      Then user validates the response of built in API
	  
	  
