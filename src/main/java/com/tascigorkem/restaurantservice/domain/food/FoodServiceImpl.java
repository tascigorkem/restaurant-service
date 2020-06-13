package com.tascigorkem.restaurantservice.domain.food;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodPersistencePort foodPersistencePort;

    public FoodServiceImpl(FoodPersistencePort foodPersistencePort) {
        this.foodPersistencePort = foodPersistencePort;
    }

    @Override
    public Mono<FoodDto> getFoodById(UUID id) {
        return foodPersistencePort.getFoodById(id);
    }
}
