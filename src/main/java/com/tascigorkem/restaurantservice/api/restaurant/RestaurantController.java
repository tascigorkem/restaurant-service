package com.tascigorkem.restaurantservice.api.restaurant;

import com.tascigorkem.restaurantservice.api.response.Response;
import com.tascigorkem.restaurantservice.domain.restaurant.RestaurantDto;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.function.Function;

public interface RestaurantController {

    @GetMapping("/restaurants")
    Mono<Response> getRestaurants();

    @GetMapping("/restaurants/{id}")
    Mono<Response> getRestaurantById(@PathVariable("id") UUID id);

    @PostMapping("/restaurants")
    Mono<Response> addRestaurant(@RequestBody RestaurantControllerRequestDto restaurantControllerRequestDto);

    @PutMapping("/restaurants/{id}")
    Mono<Response> updateRestaurant(@PathVariable("id") UUID id, @RequestBody RestaurantControllerRequestDto restaurantControllerRequestDto);

    @DeleteMapping("/restaurants/{id}")
    Mono<Response> removeRestaurant(@PathVariable("id") UUID id);

    Function<RestaurantDto, RestaurantControllerResponseDto> mapToRestaurantControllerResponseDto();

    Function<RestaurantControllerRequestDto, RestaurantDto> mapToRestaurantDto();
}
