# ScalableWebApp
This solution is used to provide 2 http endpoints that accepts JSON base64 encoded binary data on both endpoints.
The provided data needs to be diff-ed and the results shall be available on a third end
point.

## Approach
- Create Rest API specification can be created in order to document API requirements
- Spring boot along with libraries such as lombok, mapstruct can be used to implement this application. Using the framework and libraries will help in reducing need to write boiler plate code and focus on business logic.
- Database can be used to persist data coming from both end points and once Third end point is called diff-ed operation can be performed.


## Solution Details
This application provide 3 different rest end points:
- <host>/v1/diff/<ID>/left: This is POST call to save left side of the data that need to be diffed.
Here "ID" is unique identifier. 
- <host>/v1/diff/<ID>/right: This is also a POST call to save right side of the data that need to be diffed.
Here "ID" is unique identifier. 
- <host>/v1/diff/<ID>: GET call to retrieve the result of diff-ed operations of both left and right side data against the same "ID" unique identifier.

Spring boot application consist of following layers:
- controller: to expose end points
- service: to provide business logic
- repository: to interact with database
- dto: to transfer data between different layers
- entity: persistent database objects
- mapper: to map data between dto and entity
- exception: to handle exceptions

### API Specification:
- "ScalableWebApp_v1.yaml" API specification file is created first to document the APIs.
- This file can be visualize using online swagger editor(https://editor.swagger.io/).
- "openapi-generator-maven-plugin" Maven plugin is used to generate the java class representation the Request and Response objects. 

### Testing
- unit tests: JUnit + Mockito
- integration tests: Spring Boot Test

## Run locally
mvn clean install 

java -jar ScalableWebApp-0.0.1-SNAPSHOT.jar

### Improvements
Following improvements can be incorporated as next increments:
- add unit tests for checking edge cases and negative sceanarios.
- once both left and right side data is received, the diffed operation can be initiated asynchronously.
- Create Dockerfile so that the application can be run as a container.