# README.md

![springboot-tomcat](docs/springboot-tomcat.png)

This is a springboot / tomcat project for representing an ATM application

#### Build/Deploy
* mvn clean install
* mvn clean install -D skipTests=false
* mvn spring-boot:run

#### Docs/Testing
Swagger UI docs and test runner available at:

```
http:localhost:8081/swagger-ui.html
```

#### Usage:

##### Get Balance:

```
GET http:localhost:8081/accounts/123456789/1234/balance
```

JSON Response:

```
{
  "accountNumber": 123456789,
  "amount": 800
}
```


##### Make Withdrawal:

```
GET http:localhost:8081/accounts/123456789/1234/withdrawal/50
```

JSON Response:

```
{
  "accountNumber": 123456789,
  "amount": 50,
  "denominations": {"Five":1,"Ten":0,"Twenty":0,"Fifty":1},
  "balance": 750
}
```

#### Dependencies

##### Project Lombok
This springboot project uses project lombok mvn dependency to simplify development of java beans. 
It enables auto creation of getters/setters at compile time and runtime and significantly reduces builerplte LOC (lines of code).
Example project lombok usage can be found here [here](https://projectlombok.org/features/Data)


For build and runtime use, no setup is required. To use project lombok in your IDE at compile time, there
is a one time setup to set the path for project lombok in the IDE. 
This can be done with the following command: 

```
java -jar ~/.m2/repository/org/projectlombok/lombok/1.16.20/lombok-1.16.20.jar
```

Then follow the wizard setup to point your project lombok jar to the location of your IDE.

