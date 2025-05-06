#  Airline-Management-System
This is a Java-based GUI application that simulates an airline management system. It allows users to log in, book flights, add customer details, view journey and flight details, cancel tickets, and generate boarding passes. The application uses Java Swing for the user interface and JDBC for database connectivity.

- **Java (JDK 8+)**
- **Java Swing (GUI)**
- **JDBC (Database connectivity)**
- **MySQL**
- **rs2xml.jar** (for table rendering using `DbUtils`)
- **JCalendar.jar** (for calendar/date-picker components)

# Features
- Login System: Secure login for users (Login.java)

- Dashboard: Central hub for navigation (Home.java)

- Customer Management: Add and manage customer details (AddCustomer.java)

- Flight Booking: Book flights by entering PNR and flight details (BookFlight1.java)

- Journey Information: View journey details based on PNR (JourneyDetails.java)

- Flight Information: Browse all available flights (FlightInfo.java)

- Ticket Cancellation: Cancel booked tickets using PNR (Cancel.java)

- Boarding Pass Generation: Generate a boarding pass with flight and user information (BoardingPass.java)

- Database Connection: Central JDBC connection handler (Conn.java)

# File Structure

├── AddCustomer.java       -> Add customer details <br>
├── BookFlight1.java       -> Book flights<br>
├── Cancel.java            -> Cancel flight tickets<br>
├── Conn.java              -> JDBC database connection<br>
├── FlightInfo.java        -> Show available flights<br>
├── Home.java              -> Application dashboard<br>
├── JourneyDetails.java    -> View journey info using PNR<br>
├── Login.java             -> Login screen<br>
├── BoardingPass.java      -> Boarding pass generation<br>
├── Main.java              -> Entry point (for testing)<br>

# How to Run

1. Ensure Java JDK and MySQL are installed.

2. Include rs2xml.jar(or similar for date selection). and JCalendar.jar
    
3. Create the necessary tables in your MySQL database:

4. login (for authentication)

5. customer (for storing user details)

6. flight (available flights)

7. reservation (booking records)

8. Update your DB credentials in Conn.java:

- **Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourdbname", "username", "password");**<br>

9. Set Up Database

   - Create a new MySQL database, e.g., airline

   - Use the script below to create necessary tables

10. Run the application starting from Login.java.

#  Login Credentials and Database Script
*Ensure the login table in your MySQL DB has valid test credentials (username and password).*

## Database Setup (SQL Script)

CREATE DATABASE airline;
USE airline;

-- Login table
CREATE TABLE login (
    username VARCHAR(20),
    password VARCHAR(20)
);
INSERT INTO login VALUES ('admin', 'admin');

-- Passenger/customer info
CREATE TABLE passenger (
    name VARCHAR(20),
    nationality VARCHAR(20),
    phone VARCHAR(15),
    address VARCHAR(50),
    aadhar VARCHAR(20),
    gender VARCHAR(20)
);

-- Flight information
CREATE TABLE flight (
    f_code VARCHAR(20),
    f_name VARCHAR(20),
    source VARCHAR(40),
    destination VARCHAR(40)
);

INSERT INTO flight VALUES
("1001", "AI-1212", "Delhi", "Mumbai"),
("1002", "AI-1453", "Delhi", "Goa"),
("1003", "AI-1112", "Mumbai", "Chennai"),
("1004", "AI-3222", "Delhi", "Amritsar"),
("1005", "AI-1212", "Delhi", "Ayodhya");

-- Reservation details
CREATE TABLE reservation (
    PNR VARCHAR(15),
    TICKET VARCHAR(20),
    aadhar VARCHAR(20),
    name VARCHAR(20),
    nationality VARCHAR(30),
    flightname VARCHAR(15),
    flightcode VARCHAR(20),
    src VARCHAR(30),
    des VARCHAR(30),
    ddate VARCHAR(30)
);

-- Ticket cancellation
CREATE TABLE cancel (
    pnr VARCHAR(20),
    name VARCHAR(40),
    cancelno VARCHAR(20),
    fcode VARCHAR(20),
    ddate VARCHAR(30)
);

# Default Login

- Username: admin
- Password: admin
