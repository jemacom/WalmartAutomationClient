# Walmart Automation Client

###READ ME:

###INSTRUCTIONS TO RUN THE PROGRAM

You can clone the project using this command or download the zip file :

```
git clone https://github.com/kandarp1691/WalmartAutomationClient.git
```

> This is an **Eclipse** Project.

There are two ways to run the program :

1. From Eclipse 
  * Run the main method as a java application.
  * Run the test suite as a JUnit Test.

2. From the command line using `maven` :

> Move to the project directory and make sure you have maven in the `class path` .

```
 mvn -Dtest=TestClass#TestMethod test
```



### NOTES REGARDING THE SOURCE CODE:

The code currently accepts a username and password from an escaped JSON String that has been declared. 
`The cookie is taken as constant`. It has been fetched from the data obtained using `Charles Proxy`. 

For the purpose of simplicity, the method `searchResults` outputs single item name and its offer ID which can be used to specify which item and quantity to add to the cart. The search method can be modified to return a list of all the items and then the user can select the offer ID of whatever item he wishes to add to cart.

The validation of cart items is done using two methods. 
  1.cartItemsCount method returns the total count of items in the cart
  2.validateOfferIdInCart method returns the offer ID of the item in cart if there is only one item. If there are more        than 1 items, It returns that `More number of items`. Also, if cart is empty, it will return `No items in cart`.                      

### POSSIBLE ENHANCEMENTS :

> Can be included in a continuous integration pipeline so that API can be test in an automatic fashion and any harmful change can be tracked using the flow.


