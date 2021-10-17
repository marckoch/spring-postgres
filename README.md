# Small example Spring Boot App with local postgres DB

## Use PG in docker container

Run postgres DB in docker container:
```
docker run -p 5432:5432 --name my-postgres -e POSTGRES_USER=marc -e POSTGRES_PASSWORD=password -e POSTGRES_DB=testdb postgres
```
- `my-postgres` is the container name
- `testdb` is the name of the database
- `postgres` is the image name pulled from docker

then run SpringPostgresApplication
```
./mvnw spring-boot:run 
```
and observe output in console:
```
...
Number of customers: 0
Saving new customer...
Number of customers: 1
...
```

You can connect to the database with any tool, e.g. IntelliJ IDE to see data.

Please observe the `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/testdb
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=marc
spring.datasource.password=password
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
```

## Use docker compose

- build app `./mvnw clean package`
- `cp target/spring-postgres-0.0.1-SNAPSHOT.jar src/main/docker`
- `cd src/main/docker`
- `docker-compose up --build`

again observe output:

```
...
Number of customers: 0
Saving new customer...
Number of customers: 1
...
```

## Attention

- this PG DB is accessible from the outside over port 5431 (see `ports` in `docker-compose.yml`)
- observe that the `environment` in the `docker-compose` file overwrites the `application.properties`