package com.tascigorkem.restaurantservice.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface FoodController {

    @GetMapping("/food/{id}")
    FoodControllerResponseDto getFood(@PathVariable("id") Long id);

}
