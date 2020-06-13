package com.tascigorkem.restaurantservice.domain.food;

import reactor.core.publisher.Mono;

public interface FoodPersistencePort {

    Mono<FoodDto> getFoodById(Long fakeFoodId);
}
