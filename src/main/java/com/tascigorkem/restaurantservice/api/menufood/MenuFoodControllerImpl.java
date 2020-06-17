package com.tascigorkem.restaurantservice.api.menufood;

import com.tascigorkem.restaurantservice.api.response.Response;
import com.tascigorkem.restaurantservice.domain.menufood.MenuFoodDto;
import com.tascigorkem.restaurantservice.domain.menufood.MenuFoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.Function;

@Slf4j
@RestController
public class MenuFoodControllerImpl implements MenuFoodController {

    private final MenuFoodService menuFoodService;

    public MenuFoodControllerImpl(MenuFoodService menuFoodService) {
        this.menuFoodService = menuFoodService;
    }

    @Override
    public Mono<Response> getAllMenuFoods() {
        return menuFoodService.getAllMenuFoods()
                .map(mapToMenuControllerResponseDto())
                .collectList()
                .map(Response.ok()::setPayload)
                .cast(Response.class);
    }

    @Override
    public Mono<Response> getFoodPriceInfoByMenuId(UUID menuId, UUID foodId) {
        return menuFoodService.getFoodPriceInfoByMenuId(menuId, foodId)
                .map(mapToMenuControllerResponseDto())
                .map(Response.ok()::setPayload)
                .cast(Response.class);
    }

    @Override
    public Function<MenuFoodDto, MenuFoodControllerResponseDto> mapToMenuControllerResponseDto() {
        return menuFoodDto ->
                MenuFoodControllerResponseDto.builder()
                        .id(menuFoodDto.getId())
                        .menuId(menuFoodDto.getMenuId())
                        .foodId(menuFoodDto.getFoodId())
                        .foodName(menuFoodDto.getFoodName())
                        .originalPrice(menuFoodDto.getOriginalPrice())
                        .extended(menuFoodDto.isExtended())
                        .extendedPrice(menuFoodDto.getExtendedPrice())
                        .build();
    }
}
