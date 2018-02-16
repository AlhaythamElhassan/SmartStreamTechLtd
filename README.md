# Orders Application
[![CircleCI](https://circleci.com/gh/AlhaythamElhassan/SmartStreamTechLtd/tree/master.svg?style=svg)](https://circleci.com/gh/AlhaythamElhassan/SmartStreamTechLtd/tree/master)

The project read data from two files `Person.data` and `Order.data` located under `$ProjectRoodDir/src/main/resourses/` directory. Then it create and populate two database tables 
## How to run the project 
- Open a terminal 
- unzip the zip folder
- Navigate to the project root directory (the unzipped folder)
- start database server by issuing following command form the terminal
     ```sh 
    java -jar src/Database/db-derby-10.14.1.0-bin/lib/derbyrun.jar server start 
    ```
    this will starts Derby Embedded database server 
- To run the application issue 
    ```sh 
    ./gradlew eclipse
    ./gradlew run
- To run test cases issue 
    ```sh 
    ./gradlew test  
## Testing Framework
This project uses **JUnit Platform + JUnit Jupiter + JUnit Vintage** as a testing framwork. 
### Notes:
 - To be easy to separate production code from test classes, later ones can be put in packages that are different the production 	ones.
 - There is a possibility that Derby database server starts at different port the default is port 1527. in case that happened 
 	please update portNumber field in component Database in package database in path 
 	```sh
 	$ProjectRootDir/src/main/java/database/Database.java
 	
