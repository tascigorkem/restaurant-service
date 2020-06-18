## Restaurant Service for Food Order System

<p align="center">
    <a alt="Java">
        <img src="https://img.shields.io/badge/Java-v1.11-orange.svg" />
    </a>
    <a alt="Spring Boot">
        <img src="https://img.shields.io/badge/Spring%20Boot-v2.3.1-brightgreen.svg" />
    </a>
    <a alt="Dependencies">
        <img src="https://img.shields.io/badge/dependencies-up%20to%20date-brightgreen.svg" />
    </a>
    <a alt="Contributions">
        <img src="https://img.shields.io/badge/contributions-welcome-orange.svg" />
    </a>
    <a alt="Licence">
        <img src="https://img.shields.io/github/license/tascigorkem/flight-reservation-api">
    </a>
</p>

RESTful Restaurant Service developed with Spring WebFlux and R2DBC (Postgesql) in Spring Boot.

### Hexagonal architecture

Used hexagonal architecture with TDD for this project.

Example Flow:

<img src="./docs/hexa-diagram.png" alt="" width="600">

<img src="./docs/project-structure.png" alt="" width="400">

## CI & Deployment

`Github` repo connected `Travis CI` and triggered after every push to the `master` branch, then build application and running tests and started deploy to `Heroku`.

Connected `Logentries` distributed tracing tool to Heroku Application for monitoring.

#### Travis CI

Looking `.travis.yml` in beginning to build and takes CI configurations.

`...`

`mvn clean install -DskipITs`

``...``

Screenshot: `./docs/travis-ci.pdf`

<img src="./docs/travis-ci.png" alt="" width="800">

#### Heroku

Looking `Procfile` (below) in beginning to deploy and takes environment variables.

`web: java -Dserver.port=$PORT -Dspring.profiles.active=heroku $JAVA_OPTS -jar target/restaurant-service-0.0.1-SNAPSHOT.jar`

Screenshot: `./docs/heroku-dashboard.pdf`

<img src="./docs/heroku.png" alt="" width="800">

#### Logentries

Screenshot: `./docs/logentries-1.pdf`

Screenshot: `./docs/logentries-2.pdf`

Screenshot: `./docs/logentries-3.pdf`

<img src="./docs/logentries-3.png" alt="" width="800">

## Docker

Application dockerized via `./Dockerfile` and pushed `DockerHub`. 

https://hub.docker.com/r/tascigorkem/restaurant-service

## Tests

Run `mvn integration-test` for Integration Tests (**/*IT.class)

Run `mvn test` for Unit Tests (**/*Test.class)

#### Test Pattern

Used patterns:

* TDD (Test Driven Development)

* 3A (Arrange, Act, Assert) 

* Given, When, Then and BDD (Behavior-Driven Development)

for unit tests and integration tests.

<img src="./docs/unit-test.png" alt="" width="500">

#### Test Coverage

98% classes, 96% lines covered in project.

<img src="./docs/test-coverege.png" alt="" width="500">

## Database

#### Postgres - R2DBC

Used Postgres for DB and R2DBC for Postgres for CRUD operations.

Database Schema Generation - Sql: `./src/test/resources/schema.sql`

Database Example Inserts - Sql: `./src/test/resources/inserts.sql`

Database Credentials: `./src/main/resources/application-heroku.properties`

<img src="./docs/diagram.png" alt="javadoc" width="600">

## Docs

Docs located `./docs` folder in project main folder.

#### Javadoc

Used javadoc standarts for method comments.

Run `mvn javadoc:javadoc` for create javadocs.

<img src="./docs/javadoc.png" alt="javadoc" width="800">

#### Swagger & Api-Docs

Used Swagger and Api-Docs for RestAPI endpoints.

https://cb-restaurant-service.herokuapp.com/swagger-ui.html

https://cb-restaurant-service.herokuapp.com/api-docs

<img src="./docs/add-food-request.png" alt="" width="800">

<img src="./docs/get-all-foods-request.png" alt="" width="800">

## Problems & Notes

Proje gerçekten zorlayıcıydı, geliştirirken oldukça keyif aldım, bazı noktalarda deneyimim pek yoktu 
ve bu 17 Haziran Salı akşamı geçişimiz olduğu için projeye zaman yaratmakta da zorlandım, 
bunun sonucunda yaşadığım çözemediğim problemler ve notlar:

**1 -** Projeyi dockerize ettim, fakat oluşan image'i deploy etmeyi başaramadım. Fakat DockerHub'a push'ladım oradan pull edilebilir.
Docker image'ı deploy edemeyince docker-compose kullanmak istedim. postgres ve spring boot 
uygulamayı tek bir compose dosyasına yazdım. O da lokalde çalışmasına rağmen deploy etmeyi başaramadım. Daha fazla 
burada zaman kaybetmek istemedim.

**2 -** Postgres db'si Heruko üzerinde ayakta olduğu için Travis'te proje ayağa kalkarken integration test'ler
db'ye ssl hatasından hatasından dolayı erişemiyor ve hata oluyor. Bu Travis'te proje build olurken sadece unit 
test'leri çalıştırıyor. `mvn clean install -DskipITs` ile IT'ler skip ediliyor.

**3 -** Spring Reactive Webflux kullandığımdan dolayı DB tarafında JPA, Hibernate kullanamadım. 
Bunun yerine R2DBC for Postgres kullandım. Bu yüzden `@OneToMany` `@CreationTimestamp` gibi bazı annotation'lar kullanılamadı.

**4 -** Restaurant tarafından fiyatın ezilip ezilmediğini öğrenmek için `menu_food` tablosundaki `extended` kolonuna bakıyorum. Eğer
`extended` değeri true ise o `menu` entity'si için `food` entity'sinin üzerindeki `price` kolonunu değil; `menu_food` tablosundaki
`extended_price` kolonundaki değeri ön tarafa getiriyorum.

Örnek istek: "Scotch Eggs" food kaydının normal fiyarı 20.57, fakat menu'de extend edilmiş ve bu menü için fiyatı 26.56 olmuş.

https://cb-restaurant-service.herokuapp.com/menus/8b03175c-af6c-4cab-a958-70d53369fe5c/foods/1a713ef5-0078-452d-958e-770fbb797797

`GET: /menus/{menuId}/foods/{foodId}`
```json
{
     "statusCode": 200,
     "status": "OK",
     "payload": {
         "id": "c91160e7-0820-425f-8def-17442672c48a",
         "menuId": "8b03175c-af6c-4cab-a958-70d53369fe5c",
         "foodId": "1a713ef5-0078-452d-958e-770fbb797797",
         "foodName": "Scotch Eggs",
         "originalPrice": 20.57,
         "extended": true,
         "extendedPrice": 26.56
     }
 }
 ```
https://cb-restaurant-service.herokuapp.com/menus/8b03175c-af6c-4cab-a958-70d53369fe5c/
 
`GET: /menus/{id}`
 
 ```json
 {
     "statusCode": 200,
     "status": "OK",
     "payload": {
         "id": "8b03175c-af6c-4cab-a958-70d53369fe5c",
         "name": "magni",
         "menuType": "et",
         "restaurantId": "9d7cfe42-e7dd-44b5-b01b-2fbaa6dd619e"
     }
 }
 ```
 
 
https://cb-restaurant-service.herokuapp.com/foods/1a713ef5-0078-452d-958e-770fbb797797
 
`GET: /foods/{id}`
 

```json
{
    "statusCode": 200,
    "status": "OK",
    "payload": {
        "id": "1a713ef5-0078-452d-958e-770fbb797797",
        "name": "Scotch Eggs",
        "vegetable": false,
        "price": 20.57,
        "imageUrl": "www.tonita-langosh.biz"
    }
}
```


## References

https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html

https://docs.spring.io/spring-data/r2dbc/docs/1.1.0.RELEASE/reference/html/#reference

Görkem Taşçı,

2020
