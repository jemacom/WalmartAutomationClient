# WalmartAutomationClient

READ ME:

INSTRUCTIONS TO RUN THE PROGRAM

Download the source code from the following GitHub Link.  
This is an Eclipse Project. There are two ways to run the program 
I have added a main method in case you want to run it as a Java Application.
I have also provided a test suite if you want to run it using that.
3.  If you are running this project from Eclipse, you can run the main method as a Java 		  
     and can run the test as a JUnit Test.
4.  If you are running the project from command line, make sure you have maven added to the       class path.       Running test from maven use mvn -Dtest=TestClass#TestMethod test




NOTES REGARDING THE SOURCE CODE:

The code currently accepts username password from an escaped JSON String that has been declared. 
The cookie is taken as constant. It has been fetched from the data obtained using Charles Proxy. 
For the purpose of simplicity, the method searchResults outputs single item name and its offer ID which can be used to specify which item and quantity to add to the cart. The search method can be modified to return a list of all the items and then the user can select the offer ID of whatever item he wishes to add to cart. 
Validation of cart items is done using two methods. 
     a) cartItemsCount method returns the total count of items in the cart
     b) validateOfferIdInCart method returns the offer ID of the item in cart if there is only one item. If there are more        than 1 items, It returns that “More number of items”. Also, if cart is empty, it will return “No items in cart”.                       

ENHANCEMENTS POSSIBLE:

Can be included in a continuous integration pipeline so that API can be test in an automatic fashion and any harmful change can be tracked using the flow.


