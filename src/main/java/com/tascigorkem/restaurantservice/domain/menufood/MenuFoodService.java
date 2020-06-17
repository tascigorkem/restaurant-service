package com.tascigorkem.restaurantservice.domain.menufood;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface MenuFoodService {

    Flux<MenuFoodDto> getAllMenuFoods();

    Mono<MenuFoodDto> getFoodPriceInfoByMenuId(UUID menuId, UUID foodId);
}
