# Integrate Two DBMS Middleware

## Description

A middleware application that integrate data from 2 DBMS: SQLServer and MongoDB

## Get Started
### Prerequisites
* Java (Version 20) (https://www.oracle.com/java/technologies/downloads/) 
* Maven (https://maven.apache.org/download.cgi)
### Configuration
* Configuration properties can be found in the application.properties file located in the src/main/resources directory.
<br>
* Change `xxx` value with you value
### Running the application
1. Clone the repository:
```
git clone https://github.com/MrThinkJ/IntegrateDBMSMiddleware.git
```
2. Navigate to the project directory:
```
cd IntegrateMiddlewareAppicatlion
```
3. Run the application:
```
mvn spring-boot:run
```
This will start the application on port [8080] by default. You can access the application at http://localhost:[8080].
### Building the application
You can build a JAR file for your application using the following command:
```
mvn package
```
The JAR file will be located in the target directory.
### Testing
Unit tests are located in the src/test/java directory. You can run the tests using the following command:
```
mvn test
```
### API Document
http://localhost:8080/swagger-ui/index.html