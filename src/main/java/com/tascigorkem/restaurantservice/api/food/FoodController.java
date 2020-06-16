package com.tascigorkem.restaurantservice.api.food;

import com.tascigorkem.restaurantservice.api.response.Response;
import com.tascigorkem.restaurantservice.domain.food.FoodDto;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.Function;

public interface FoodController {

    @GetMapping("/foods")
    Mono<Response> getFoods();

    @GetMapping("/foods/{id}")
    Mono<Response> getFoodById(@PathVariable("id") UUID id);

    @PostMapping("/foods")
    Mono<Response> addFood(@RequestBody FoodControllerRequestDto foodControllerRequestDto);

    @PutMapping("/foods")
    Mono<Response> updateFood(@RequestBody FoodControllerRequestDto foodControllerRequestDto);

    @DeleteMapping("/foods/{id}")
    Mono<Response> removeFood(@PathVariable("id") UUID id);

    Function<FoodDto, FoodControllerResponseDto> mapToFoodControllerResponseDto();

    Function<FoodControllerRequestDto, FoodDto> mapToFoodDto();
}
