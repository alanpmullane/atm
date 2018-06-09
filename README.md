

mvn clean install
mvn clean install -D skipTests=false
mvn spring-boot:run

Swagger docs and REST runner:
http:localhost:8081/swagger-ui.html

Account 123456789
Get Balance:
GET http:localhost:8081/accounts/123456789/1234/balance
Make Withdrawal:
POST http:localhost:8081/accounts/123456789/1234/withdrawal
{ "amount": 50 }

Account 987654321
Get Balance:
GET http:localhost:8081/accounts/987654321/4321/balance
Make Withdrawal:
POST http:localhost:8081/accounts/987654321/4321/withdrawal
{ "amount": 100 }

