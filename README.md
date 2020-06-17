## Restaurant Service for Food Order System

RESTful Service with Spring Boot

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

This application developed with Spring WebFlux and R2DBC in Spring Boot.

### Hexagonal architecture

Used hexagonal architecture for this project.

Example Flow:

Client -> CompanyController (I) -> CompanyControllerImpl -> 

CompanyService (I) -> CompanyServiceImpl -> 

CompanyPersistencePort (I) -> CompanyPersistenceAdapter 

-> CompanyRepository (I) -> ReactiveCrudRepository (I) -> DB

<img src="./docs/project-structure.png" alt="project-structure" width="400">


<div align="center">
<h4>Test Coverage</h4>
  <img src="./docs/test-coverege.png" alt="test-coverege" width="400">
</div>

http://localhost:8080/api-docs

http://localhost:8080/swagger-ui.html

https://cb-restaurant-service-staging.herokuapp.com/

https://cb-restaurant-service-prod.herokuapp.com/