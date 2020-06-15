package com.tascigorkem.restaurantservice.api.food;

import com.tascigorkem.restaurantservice.api.response.Response;
import com.tascigorkem.restaurantservice.domain.exception.FoodNotFoundException;
import com.tascigorkem.restaurantservice.domain.food.FoodService;
import lombok.extern.slf4j.Slf4j;
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
    public Mono<Response> getFoodById(UUID id) {
        return foodService.getFoodById(id)
                .map(foodDto ->
                        FoodControllerResponseDto.builder()
                                .id(foodDto.getId())
                                .name(foodDto.getName())
                                .vegetable(foodDto.isVegetable())
                                .build())
                .map(foodDto -> Response.ok().setPayload(foodDto))
                .cast(Response.class)
                .switchIfEmpty(Mono.just(
                        Response.notFound().setPayload(
                                new FoodNotFoundException("id", id.toString()).getMessage())
                ));
    }
}
