Test-3

Project Overview
Test-3 is a Java-based Pizza Ordering Application designed to provide users with an interactive interface to place pizza orders, manage menu items, calculate the total bill, and store order details in a database. The application demonstrates fundamental Java programming concepts, JavaFX GUI design, and database connectivity using JDBC.
 Features
- View and select different types of pizzas with various sizes and toppings.
- Calculate the total price based on selected pizza options.
- Save and retrieve orders using a MySQL database.
- Basic CRUD operations on pizza orders.
- User-friendly GUI built with JavaFX.
Technologies Used
- Java 8+
- JavaFX for GUI
- MySQL for database
- JDBC for database connectivity

Setup Instructions
Prerequisites
- Java Development Kit (JDK) 8 or higher installed.
- MySQL Server installed and running.
- An IDE like IntelliJ IDEA, Eclipse, or VS Code.
atabase Setup
1. Create a MySQL database named `pizza_order_db` (or your preferred name).
2. Import or execute the provided SQL scripts in the `database` folder to create necessary tables.
3. Update the database connection details (URL, username, password) in the DAO class to match your MySQL setup.
Running the Application
1. Clone this repository:
git clone https://github.com/01Niraj01/Test-3.git
2. Open the project in your preferred IDE.
3. Build the project and run the `Main` class (or the JavaFX Application entry point).
4. Use the GUI to select pizza options and place your order.
Project Structure
- `src/` - Contains all source code files.
- `controllers/` - Contains JavaFX controller classes.
- `models/` - Contains model classes representing pizza and order data.
- `dao/` - Data Access Object classes for database interaction.
- `views/` - FXML and UI layout files (if any).
- `database/` - SQL scripts for creating tables.

## Contribution
Contributions are welcome! Feel free to fork the repository and submit pull requests.

Note: Codes here are some part of Ai. I havve used different useful websites like W3Schools for the best version of my code.
Author
Niraj Bhandari
