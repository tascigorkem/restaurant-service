package com.tascigorkem.restaurantservice.api.food;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface FoodController {

    @GetMapping("/foods/{id}")
    Mono<ResponseEntity> getFoodById(@PathVariable("id") UUID id);

}
