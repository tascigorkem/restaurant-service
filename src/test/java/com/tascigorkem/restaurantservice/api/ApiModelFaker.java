package com.tascigorkem.restaurantservice.api;

import com.github.javafaker.Faker;
import com.tascigorkem.restaurantservice.api.company.CompanyControllerRequestDto;
import com.tascigorkem.restaurantservice.api.food.FoodControllerRequestDto;

import java.math.BigDecimal;
import java.util.UUID;

public class ApiModelFaker {

    private static Faker faker = Faker.instance();

    public static FoodControllerRequestDto getFoodControllerRequestDto() {
        return FoodControllerRequestDto.builder()
                .name(faker.food().dish())
                .vegetable(faker.bool().bool())
                .price(BigDecimal.valueOf(faker.number().randomDouble(2, 1, 10)))
                .imageUrl(faker.internet().url())
                .build();
    }

    public static CompanyControllerRequestDto getCompanyControllerRequestDto() {
        return CompanyControllerRequestDto.builder()
                .name(faker.company().name())
                .address(faker.address().fullAddress())
                .phone(faker.phoneNumber().phoneNumber())
                .emailAddress(faker.internet().emailAddress())
                .websiteUrl(faker.internet().url())
                .build();
    }

    public static UUID fakeId() {
        return UUID.randomUUID();
    }
}
