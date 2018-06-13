# README.md
This is mvn project for atm application

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


