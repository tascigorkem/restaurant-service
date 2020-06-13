package com.tascigorkem.restaurantservice.infrastructure.food;

import com.tascigorkem.restaurantservice.domain.food.FoodDto;
import com.tascigorkem.restaurantservice.domain.food.FoodPersistencePort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class FoodPersistenceAdapter implements FoodPersistencePort {

    private final FoodRepository foodRepository;

    public FoodPersistenceAdapter(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public Mono<FoodDto> getFoodById(UUID id) {
        return foodRepository.findById(id).map(this::mapToFoodDto);
    }

    protected FoodDto mapToFoodDto(FoodEntity foodEntity) {
        return FoodDto.builder()
                .id(foodEntity.getId())
                .name(foodEntity.getName())
                .vegetable(foodEntity.isVegetable())
                .build();
    }

    protected FoodEntity mapToFoodEntity(FoodDto foodDto) {
        return FoodEntity.builder()
                .id(foodDto.getId())
                .name(foodDto.getName())
                .vegetable(foodDto.isVegetable())
                .build();
    }
}
