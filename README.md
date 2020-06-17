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

`Client <-> ` 

`CompanyController (I) <-> CompanyControllerImpl <->` 

`CompanyService (I) <-> CompanyServiceImpl <-> `

`CompanyPersistencePort (I) <-> CompanyPersistenceAdapter <-> `

`CompanyRepository (I) <-> ReactiveCrudRepository (I) <-> DB <-> `

`DB`

<img src="./docs/project-structure.png" alt="" width="400">

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

## Docs

Docs located ./docs folder in project main folder.

#### Swagger & Api-Docs

Used Swagger and Api-Docs for RestAPI endpoints.

https://cb-restaurant-service.herokuapp.com/swagger-ui.html

https://cb-restaurant-service.herokuapp.com/api-docs

<img src="./docs/add-food-request.png" alt="" width="800">
<img src="./docs/get-all-foods-request.png" alt="" width="800">


#### javadoc

Run `mvn javadoc:javadoc` for create javadocs.

<img src="./docs/javadoc.png" alt="javadoc" width="800">

