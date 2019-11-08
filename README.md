# Mechanic Shop
Welcome to Amir's Mighty Mechanic Shop. This is a JavaFX application that connects to a local PostgreSQL database. 
I originally wrote this project on the terminal for my Database Management Systems class at UC Riverside.
I expanded it using JavaFX which provides a GUI.

# Introduction
This program intends to perform the following functions:

1. Connect to a local mechanic shop PSQL database
2. Add customers
3. Add customers' cars
4. Add mechanics
5. Open service requests for cars
6. Close service requests
7. List specific records

# How to run this application
This program has been built and tested on Ubuntu 18.04 including PostgreSQL 10, OpenJDK 11, JavaFX Linux SDK 11.

1. You need [PostgreSQL](https://www.postgresql.org/download/).
2. You need the Java Runtime Environment (JRE) `sudo apt install default-jre` Verify your installation with `java -version`
3. You need [JavaFX](https://gluonhq.com/products/javafx/)
4. `sudo -u postgres createuser --interactive`
5. Input your login name. Answer 'y' on superuser prompt
6. `createdb mechanic_shop`
7. `cd` to the data folder
7. `psql mechanic_shop < ../sql/create.sql`
8. `java --module-path %PATH_TO_JAVAFX_FOLDER% --add-modules javafx.controls,javafx.fxml -jar %PATH_TO_JavaFXMechanicShop.jar` Make sure you include `lib/` when pathing to the JavaFX folder.

# How it Works
1. Obtains a physical connection to the database `DriverManager.getConnection(url, username, password)`.
2. Sends a query to the connection whenever you click on one of the menu options, e.g, `SELECT MAX(id) FROM customer`.
3. Parses the query returned values and displays them on the GUI.
