package com.tascigorkem.restaurantservice.domain.food;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FoodServiceImpl implements FoodService {

    @Override
    public Mono<FoodDto> getFoodById(Long id) {
        return Mono.empty();
    }
}
