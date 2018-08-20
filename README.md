# Speed Car Test Service
This is a Standalone Spring boot MVC project with TDD demonstrates the online driver car services API.

## Introduction:
Technologies used as part of Implementation:

	- Java 1.8
	- Spring MVC with Spring Boot
	- Database H2 (In-Memory)
	- Maven
	- Junit

## 1. In Memory H2 Database is used.
    So you can modify application.properies as per your loving database. 

## 2. Running as a Packaged Application (Following ways)
    Way-1 : java -jar target\need_for_speed-0.0.1-SNAPSHOT.jar

    Way-2 : mvn clean package spring-boot:run (using maven)


#------------------------------------------------------------------------------------------------------------------
# Results can be verified by accessing following:

## In Local:
Swagger UI is configured in this application to provide rest endpoints.
So if you want to test the API, you can visit http://localhost:8080

### example login info(you can also use some users from resources/data.sql). 
user name: driver01
password: driver01pw

To access in memory DB(h2 console): https://localhost:8080/h2-console/

Driver Class: org.h2.Driver
JDBC URL: jdbc:h2:mem:speed-app;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
User Name: sa
Password: 


## Cloud (HEROKU public URL):

To inspect and access endpoints using Swagger-UI: https://speed-car.herokuapp.com/

### example login info.
user name: driver01
password: driver01pw

