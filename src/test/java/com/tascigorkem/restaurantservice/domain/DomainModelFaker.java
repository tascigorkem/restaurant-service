package com.tascigorkem.restaurantservice.domain;

import com.github.javafaker.Faker;
import com.tascigorkem.restaurantservice.domain.food.FoodDto;
import com.tascigorkem.restaurantservice.util.DateUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class DomainModelFaker {

    private static Faker faker = Faker.instance();

    public static FoodDto getFakeFoodDto(UUID id) {
        LocalDateTime now = DateUtil.getInstance().convertToLocalDateTime(new Date());

        return FoodDto.builder()
                .id(id)
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
