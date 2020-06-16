package com.tascigorkem.restaurantservice.domain.food;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface FoodService {

    Flux<FoodDto> getAllFoods();

    Mono<FoodDto> getFoodById(UUID id);

    Mono<FoodDto> addFood(FoodDto foodDto);

    void updateFood(FoodDto fakeFoodDto);

    void removeFood(UUID id);

}
