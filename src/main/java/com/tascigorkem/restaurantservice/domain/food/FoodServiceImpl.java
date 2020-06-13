package com.tascigorkem.restaurantservice.domain.food;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodPersistencePort foodPersistencePort;

    public FoodServiceImpl(FoodPersistencePort foodPersistencePort) {
        this.foodPersistencePort = foodPersistencePort;
    }

    @Override
    public Mono<FoodDto> getFoodById(Long id) {
        return foodPersistencePort.getFoodById(id);
    }
}
