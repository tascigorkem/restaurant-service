package com.tascigorkem.restaurantservice.domain;

import com.github.javafaker.Faker;
import com.tascigorkem.restaurantservice.domain.food.FoodDto;

import java.util.UUID;

public class DomainModelFaker {

    private static Faker faker = Faker.instance();

    public static FoodDto getFakeFoodDto(UUID id) {
        return FoodDto.builder()
                .id(id)
                .name(faker.food().dish())
                .vegetable(faker.bool().bool())
                .build();
    }

    public static UUID fakeFoodId() {
        return UUID.randomUUID();
    }
}
