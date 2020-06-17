package com.tascigorkem.restaurantservice.domain.menufood;

import reactor.core.publisher.Mono;

import java.util.UUID;

public interface MenuFoodService {

    Mono<MenuFoodDto> getFoodPriceInfoByMenuId(UUID menuId, UUID foodId);
}
