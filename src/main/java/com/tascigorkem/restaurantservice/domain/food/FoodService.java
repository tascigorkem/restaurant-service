package com.tascigorkem.restaurantservice.domain.food;

import reactor.core.publisher.Mono;

public interface FoodService {

    Mono<FoodDto> getFoodById(Long id);

}
