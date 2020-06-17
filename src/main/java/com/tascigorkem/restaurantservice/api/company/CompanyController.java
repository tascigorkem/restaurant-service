package com.tascigorkem.restaurantservice.api.company;

import com.tascigorkem.restaurantservice.api.response.Response;
import com.tascigorkem.restaurantservice.domain.company.CompanyDto;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.Function;

public interface CompanyController {

    @GetMapping("/companies")
    Mono<Response> getCompanies();

    @GetMapping("/companies/{id}")
    Mono<Response> getCompanyById(@PathVariable("id") UUID id);

    @PostMapping("/companies")
    Mono<Response> addCompany(@RequestBody CompanyControllerRequestDto companyControllerRequestDto);

    @PutMapping("/companies/{id}")
    Mono<Response> updateCompany(@PathVariable("id") UUID id, @RequestBody CompanyControllerRequestDto companyControllerRequestDto);

    @DeleteMapping("/companies/{id}")
    Mono<Response> removeCompany(@PathVariable("id") UUID id);

    Function<CompanyDto, CompanyControllerResponseDto> mapToCompanyControllerResponseDto();

    Function<CompanyControllerRequestDto, CompanyDto> mapToCompanyDto();
}
