package com.tascigorkem.restaurantservice.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class FoodControllerImpl implements FoodController {

    @Override
    public FoodControllerResponseDto getFood(Long id) {
        return new FoodControllerResponseDto(id, "", "");
    }
}
