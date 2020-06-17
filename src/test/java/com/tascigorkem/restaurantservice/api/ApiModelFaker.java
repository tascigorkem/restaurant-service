package com.tascigorkem.restaurantservice.api;

import com.github.javafaker.Faker;
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

    public static UUID fakeFoodId() {
        return UUID.randomUUID();
    }
}
