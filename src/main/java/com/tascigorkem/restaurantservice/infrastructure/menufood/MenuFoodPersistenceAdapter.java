package com.tascigorkem.restaurantservice.infrastructure.menufood;

import com.tascigorkem.restaurantservice.domain.exception.MenuFoodNotFoundException;
import com.tascigorkem.restaurantservice.domain.menufood.MenuFoodDto;
import com.tascigorkem.restaurantservice.domain.menufood.MenuFoodPersistencePort;
import com.tascigorkem.restaurantservice.infrastructure.food.FoodRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class MenuFoodPersistenceAdapter implements MenuFoodPersistencePort {

    private final FoodRepository foodRepository;
    private final MenuFoodRepository menuFoodRepository;

    public MenuFoodPersistenceAdapter(FoodRepository foodRepository, MenuFoodRepository menuFoodRepository) {
        this.foodRepository = foodRepository;
        this.menuFoodRepository = menuFoodRepository;
    }

    @Override
    public Mono<MenuFoodDto> getFoodPriceInfoByMenuId(UUID menuId, UUID foodId) {
        return menuFoodRepository.findAllByMenuIdAndFoodId(menuId, foodId).flatMap(menuFoodEntity ->
                foodRepository.findById(foodId).flatMap(foodEntity -> {
                    MenuFoodDto menuFoodDto = this.mapToMenuFoodDto(menuFoodEntity);
                    menuFoodDto.setOriginalPrice(foodEntity.getPrice());
                    menuFoodDto.setFoodName(foodEntity.getName());
                    return Mono.just(menuFoodDto);
                }))
                .switchIfEmpty(
                        Mono.error(new MenuFoodNotFoundException("menu id - food id", menuId.toString() + " " + foodId.toString())));
    }

    protected MenuFoodDto mapToMenuFoodDto(MenuFoodEntity menuFoodEntity) {
        return MenuFoodDto.builder()
                .id(menuFoodEntity.getId())
                .menuId(menuFoodEntity.getMenuId())
                .foodId(menuFoodEntity.getFoodId())
                .extended(menuFoodEntity.isExtended())
                .extendedPrice(menuFoodEntity.getExtendedPrice())
                .build();
    }

    public MenuFoodEntity mapToMenuFoodEntity(MenuFoodDto menuFoodDto) {
        return MenuFoodEntity.builder()
                .id(menuFoodDto.getId())
                .extended(menuFoodDto.isExtended())
                .extendedPrice(menuFoodDto.getExtendedPrice())
                .menuId(menuFoodDto.getMenuId())
                .foodId(menuFoodDto.getFoodId())
                .build();
    }
}
