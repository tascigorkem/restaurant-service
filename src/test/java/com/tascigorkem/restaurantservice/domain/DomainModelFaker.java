package com.tascigorkem.restaurantservice.domain;

import com.github.javafaker.Faker;
import com.tascigorkem.restaurantservice.domain.food.FoodDto;

public class DomainModelFaker {

    private static Faker faker = Faker.instance();

    public static FoodDto getFakeFoodDto(Long id) {
        return FoodDto.builder()
                .id(id)
                .name(faker.name().firstName())
                .vegetable(faker.bool().bool())
                .build();
    }

    public static Long fakeFoodId() {
        return faker.number().numberBetween(1L, 1000L);
    }
}
