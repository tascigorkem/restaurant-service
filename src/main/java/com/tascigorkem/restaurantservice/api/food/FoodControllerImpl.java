package com.tascigorkem.restaurantservice.api.food;

import com.tascigorkem.restaurantservice.domain.food.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RestController
public class FoodControllerImpl implements FoodController {

    private final FoodService foodService;

    public FoodControllerImpl(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    public Mono<ResponseEntity> getFoodById(UUID id) {
        return foodService.getFoodById(id)
                .map(foodDto ->
                        FoodControllerResponseDto.builder()
                                .id(foodDto.getId())
                                .name(foodDto.getName())
                                .vegetable(foodDto.isVegetable())
                                .build())
                .map(foodDto -> ResponseEntity.status(HttpStatus.OK).body(foodDto))
                .cast(ResponseEntity.class);
    }
}
