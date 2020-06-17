package com.tascigorkem.restaurantservice.api.menufood;

import com.tascigorkem.restaurantservice.api.response.Response;
import com.tascigorkem.restaurantservice.domain.menufood.MenuFoodDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.Function;

public interface MenuFoodController {

    @GetMapping("/menus/{menuId}/foods/{foodId}")
    Mono<Response> getFoodPriceInfoByMenuId(@PathVariable("menuId") UUID menuId, @PathVariable("foodId") UUID foodId);

    Function<MenuFoodDto, MenuFoodControllerResponseDto> mapToMenuControllerResponseDto();

}
