package com.tascigorkem.restaurantservice.api;

import com.github.javafaker.Faker;
import com.tascigorkem.restaurantservice.api.food.FoodControllerRequestDto;

import java.util.UUID;

public class ApiModelFaker {

    private static Faker faker = Faker.instance();

    public static FoodControllerRequestDto getFoodControllerRequestDto() {
        return FoodControllerRequestDto.builder()
                .name(faker.food().dish())
                .vegetable(faker.bool().bool())
                .build();
    }

    public static UUID fakeFoodId() {
        return UUID.randomUUID();
    }
}
