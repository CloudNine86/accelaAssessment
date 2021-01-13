# accelaAssessment
TechnicalAssessment
This project is a command line based application to basically perform crud operations.
The application make use of SpringBoot, Project Lombok, Hibernate, H2 Embedded Database and Maven.
To execute the application using maven, just enter terminal window, move to the inner project folder "accela\demo" and execute this command:
mvnw spring-boot:run
The application provides a main menù:
Choose the operation you want to perform, typing a number from 1 to 8 and press enter.
Insert the required data and press enter.  
If the application requires an id, type "quit" and on the main menù type 1, this will list all the persons and related address you persisted on the system.
The application will require ids only when modyfing or deleting an existing object, so be sure to create the object first.
