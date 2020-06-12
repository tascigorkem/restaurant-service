package com.tascigorkem.restaurantservice.api.food;

import com.tascigorkem.restaurantservice.domain.food.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class FoodControllerImpl implements FoodController {

    private final FoodService foodService;

    public FoodControllerImpl(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    public Mono<FoodControllerResponseDto> getFoodById(Long id) {
        return foodService.getFoodById(id)
                .map(foodDto ->
                        FoodControllerResponseDto.builder()
                                .id(foodDto.getId())
                                .name(foodDto.getName())
                                .vegetable(foodDto.isVegetable())
                                .build());
    }
}
