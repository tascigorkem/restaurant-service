package com.tascigorkem.restaurantservice.domain.food;

import com.tascigorkem.restaurantservice.infrastructure.food.FoodEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface FoodService {

    Flux<FoodDto> getAllFoods();

    Mono<FoodDto> getFoodById(UUID id);

    Mono<FoodDto> addFood(FoodDto foodDto);

    Mono<FoodDto> updateFood(FoodDto fakeFoodDto);

    Mono<FoodDto> removeFood(UUID id);

}
