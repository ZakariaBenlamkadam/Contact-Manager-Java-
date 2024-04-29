<div align="center">
    <h1>CMS: CONTACT MANAGEMENT SYSTEM:<br>Where Everything is Connected</h1>



This project is a JavaFX-based desktop application designed for managing personal or business contacts. It provides a user-friendly interface to store, retrieve, and manage contact details, including names, phone numbers, addresses, and related personal information. The system also supports managing contact groups, allowing for organized information management and easy access.

## Features

### User Authentication
 Secure login and logout functionality.

### Contact Management
 Add new contacts with detailed information.
 Update existing contact details.
 Delete contacts.
 Search for contacts based on various fields.

### Group Management
 Create groups to categorize contacts.
 Update and delete existing groups.
 View all contacts within specific groups.

### Database Integration
 Uses MySQL for backend data storage.

### Dynamic UI
 Responsive and intuitive user interface.

## Technologies Used
 **JavaFX**: For creating the graphical user interface.
 **MySQL**: Database for storing contact and group data.
 **JDBC**: Java Database Connectivity for database interactions.
 **Git**: For version control.

## Prerequisites
Before you begin, ensure you have the following installed on your system:
 Java JDK 11 or newer
 MySQL Server
 Git

## Setup and Installation

### Clone the Repository

To get started with the JavaFX Contact Management System, clone the repository to your local machine by running the following commands in your terminal:

```bash
git clone https://github.com/ZakariaBenlamkadam/Contact-Managemer-Java-.git
cd JavaFX-Contact-Management-System
```

## Setup and Installation

### Database Setup
Follow these steps to prepare your database for the application:

1. **Start your MySQL server.** Ensure MySQL is running on your system.
2. **Create a database named `contacts`:**
   ```sql
   CREATE DATABASE contacts;
``
**Import the contacts.sql file provided in the repo to set up the tables:**
If you have a MySQL command line tool installed, you can run:
```bash
mysql -u username -p contacts < path/to/contacts.sql
```
Replace username with your MySQL username, and provide your password when prompted.
##Configure Database Connection
Configure the connection settings in your project:

Open the DatabaseConnection.java file.
Update the database URL, username, and password to match your MySQL configuration:
```java
// Example connection settings
String url = "jdbc:mysql://localhost:3306/contacts";
String user = "yourUsername";
String password = "yourPassword";

```
## Run the Application
To run the application from an IDE:

Open the project in an IDE such as IntelliJ IDEA or Eclipse.
Build the project to resolve dependencies.
Run the HelloApplication.java file to start the application.
## Usage
After launching the application, follow these steps to use it:

Log in using the default credentials provided (or those set up in your database).
Navigate through the interface to manage contacts and groups. The application allows you to:
Add new contacts with detailed information.
Update existing contact details.
Delete contacts.
Search for contacts based on various fields.
Organize contacts into groups and manage these groups.

<h1>THE APPLICATION</h1>
  
<h3><b>This is the First Page for login </b> <h3>


![Alt text](/images/log_in.png)

<p> After you will insert the right username and password for the admin  </p>

![Alt text](/images/successLogin.png)
<p> You will click the OK button going to the main page  </p>

![Alt text](/images/MainPage.png)

<p> And this is the group Page  </p>

![Alt text](/images/groupPage.png)

<p> A simple test to add a new contact  </p>

![Alt text](/images/addedContact.png)







</div>


