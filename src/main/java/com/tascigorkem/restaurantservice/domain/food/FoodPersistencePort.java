package com.tascigorkem.restaurantservice.domain.food;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface FoodPersistencePort {

    Mono<FoodDto> getFoodById(UUID id);
}
