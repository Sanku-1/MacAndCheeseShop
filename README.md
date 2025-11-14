# Project Title
Mac n'Dulgence - Point of Sale System

## Description of the Project

Mac n'Dulgence is a custom food shop point-of-sale application that allows customers to order customized mac and cheese with various toppings, drinks, and sides. The application features a graphical user interface (GUI) built with Java Swing, user authentication, reward points system, and automatic receipt generation. Customers can personalize their mac and cheese by selecting from multiple flavors, sizes, and toppings.

## User Stories
- As a customer, I want to login to my account, so that I can access my reward points and order history.
- As a customer, I want to place an order without creating an account, so that I can quickly make a purchase.
- As a customer, I want to create a customizable mac and cheese bowl by selecting its type, size, toppings, and special options, so that I can build the exact item I want.
- As a customer, I want to review my full order with pricing and then either confirm or cancel it, so that I can verify the details before finalizing the purchase.
- As a customer, I want to see my current order and total cost before checkout, so that I can review my purchase.
- As a business owner, I want customers to be able to accumulate reward points for placing their order, so that they can be rewarded for their loyalty
- As a business, I want the system to enforce minimum purchase rules and apply correct pricing based on sizes and topping types, so that all orders follow consistent and accurate cost rules.
- As a business owner, I want receipts to be automatically saved with timestamps, so that I can keep records of all transactions.
- As a developer, I want to load store information, toppings, and customers from CSV files, so that the data is easy to manage and update.
- As a developer, I want to use object-oriented design principles, so that the code is maintainable and scalable.
  
## Setup

Instructions on how to set up and run the project using IntelliJ IDEA.

### Prerequisites

- IntelliJ IDEA: Ensure you have IntelliJ IDEA installed, which you can download from [here](https://www.jetbrains.com/idea/download/).
- Java SDK: Make sure Java SDK 17 or higher is installed and configured in IntelliJ.

### Running the Application in IntelliJ

Follow these steps to get your application running within IntelliJ IDEA:

1. Open IntelliJ IDEA.
2. Select "Open" and navigate to the directory where you cloned or downloaded the project.
3. After the project opens, wait for IntelliJ to index the files and set up the project.
4. Ensure all CSV files (storefront.csv, users.csv, meattoppings.csv, premiumtoppings.csv, standardtoppings.csv, condimenttoppings.csv, sides.csv) are in the project root directory.
5. Find the main class `ShopGUI.java` with the `public static void main(String[] args)` method.
6. Right-click on the file and select 'Run 'ShopGUI.main()'' to start the application.

## Technologies Used

- Java 17
- Java Swing (GUI Framework)

## Demo

### Screenshots
[Login](https://github.com/Sanku-1/MacAndCheeseShop/blob/master/src/main/resources/Login.png) \
[New Cart](https://github.com/Sanku-1/MacAndCheeseShop/blob/master/src/main/resources/New%20cart.png) \
[Ordering Entree](https://github.com/Sanku-1/MacAndCheeseShop/blob/master/src/main/resources/Ordering%20entree.png) \
[Checkout Empty Cart](https://github.com/Sanku-1/MacAndCheeseShop/blob/master/src/main/resources/Checkout%20empty%20cart.png) \
[Completed Checkout](https://github.com/Sanku-1/MacAndCheeseShop/blob/master/src/main/resources/Completed%20checkout.png) \
[Receipt](https://github.com/Sanku-1/MacAndCheeseShop/blob/master/src/main/resources/Receipt.png) 

## Interesting Code

### Dynamic Topping Cost Calculation

One of the most interesting pieces of code in this project is the dynamic topping cost calculation in the `MacAndCheese` class. This method calculates different prices for toppings based on the size of the mac and cheese order:

This code is interesting because it:
1. Uses Java Streams to process collections efficiently
2. Implements polymorphism by checking topping types using `instanceof`
3. Dynamically scales prices based on size (1x, 2x, 3x multipliers)
4. Uses the reduce() function to sum all topping costs
5. Demonstrates clean separation of concerns - each topping class knows its own base value

The method handles different topping types (Meat, Premium, Standard, Condiment) and automatically adjusts their prices based on the mac and cheese size, making the pricing system flexible and easy to maintain.

## Future Work

- **Order History**: Display past orders for logged-in customers
- **Signature Items**: Add pre-made signature mac and cheese combinations
- **Nutritional Information**: Display calorie and allergen information for menu items
- **Loyalty Tiers**: Implement different reward levels based on points earned
- **Aesthetics**: Improve visual experience for the user, add images to GUI, etc.

## Resources

- Java Swing Documentation: https://docs.oracle.com/javase/tutorial/uiswing/
- Java Swing Tutorial Videos: https://www.youtube.com/playlist?list=PL3bGLnkkGnuV699lP_f9DvxyK5lMFpq6U
- Java Layout Manager Tutorial: https://docs.oracle.com/javase/tutorial/uiswing/layout/using.html
- Java Border Layout Tutorial: https://docs.oracle.com/javase/tutorial/uiswing/layout/border.html
- LTCA Java Workbooks
- GeeksForGeeks Jlist guide: https://www.geeksforgeeks.org/java/java-swing-jlist-with-examples/
- GeeksForGeeks JOptionPane guide: https://www.geeksforgeeks.org/java/java-joptionpane/
- JOptionPane Tutorial: https://mkyong.com/swing/java-swing-how-to-make-a-confirmation-dialog/

## Team Members

- **Stephen Anku** - Designed and implemented all classes, GUI components, file management, and business logic throughout the project.

## Thanks

- Thank you to Raymond Maroun for continuous support and guidance throughout my Java learning journey.
- Thanks to the Java community for extensive documentation and examples.
