Feature: Selection Of A Car Details

Scenario: Get Manufacturer Details
      Given user hits the  manufacturer get call and gets the manufacturer details
      #When user gets the manufacturer details and hits the car type get call
      #Then user enters the manufacturer details and the main type details and hits the built in date
      
Scenario: Remove waKey from manufacturer api to get unauthorized response
     Given when user hits the manufacturer get call without the wakey the response returned is unauthorized.
      
Scenario: Remove locale from manufacturer api to get bad request or internal server error response.
     Given when user hits the manufacturer get call without the locale the response returned is bad request or internal server error.
     
Scenario: Remove manufacturer code from main type api code to get bad request.
     Given when user hits the main types get call without the manufacturer code the response returned is a bad request.
     
Scenario: Remove locale from main type api to get bad request or internal server error response.
     Given when user hits the main types get call without the locale but with the manufacturer code and wakey the response returned is a bad request or internal server error.
      
Scenario: Remove waKey from main type api to get unauthorized response.
     Given when user hits the main types get call without the wakey but with the manufacturer code and locale the response returned is unauthorized. 
     
Scenario: Enter two digit manufacturer code from main type api to get bad request or internal server error response
     Given when user hits the main type api with all required parameters where manufacturer code is of two digits the response returned is bad request or internal server error. 
   
Scenario: Enter arbitrary three digit manufacturer code that is not present in the dropdown
     Given when user hits the main type api with arbitrary three digit manufacturer code that is not valid the response returned is a bad request or internal server error.   
  