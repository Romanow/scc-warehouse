# Warehouse Service

```shell
# run once, it create DB services with schemas
docker compose up -d postgres
# build and run tests
./gradlew clean build
./gradlew bootRun
# open in browser http://localhost:8080/swagger-ui.html
```

Client stubs generated [contract tests](src/contractTest/resources/contracts).