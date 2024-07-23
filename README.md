# Student-Management-System

## Description

Building a student management system where users can 
- **login.**
- **view courses.**
- **Register to a course.**
- **Cancel a course registration.**
- **Get course schedule as PDF**.

## Documentation For RESTful Api Usage (Resources and Endpoints)

📧 https://documenter.getpostman.com/view/28448487/2sA3kVkgZd

# ⚙ Technologies Used ⚙
* JAX-RS (Jersey as reference implementation)
* JAX-B
* JSON-B
* Postman to test APIs
* JPA(Hibernate)
* Oracle Database version 19c
* Weblogic Application Server Latest Version (14.1.1)
* Apache Maven
* Ironpdf to generate PDF
* Java version 11
* JWT tokens
# 🛠 Work With Maven(software project management)
* Configure the pom.xml file
* Deploy the application using maven 

to generate war file (**student-management-system.war**)

`mvn clean package` used to generate the war file **student-management-system.war** to deployed into Weblogic server.

we should use the weblogic plugin to deploy the application, but here we deploy it **manually**.






*Oracle*
* Create a database schema and provide its configuration at Persistence.xml.



**cons**
- Try to handle all exceptions to be technical and business exception.
- Try to handle the jwt correctly to be in the **Authorization** header instead of the body of the request.
- Using the redis caching mechanism.
- using weblogic plugin to deploy the war file using command like 

`mvn clean package weblogic:redeploy`


need to work on the remaining points in the future isA.

[LinkedIn Account](https://www.linkedin.com/in/abdallah96)

[Personal Email](abdallahsameer22@gmail.com)

[HackerRank Account](https://www.hackerrank.com/profile/abdallahsameer22)

