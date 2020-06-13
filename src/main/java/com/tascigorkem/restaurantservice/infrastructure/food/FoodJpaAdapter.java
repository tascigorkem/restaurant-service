package com.tascigorkem.restaurantservice.infrastructure.food;

import com.tascigorkem.restaurantservice.domain.food.FoodDto;
import com.tascigorkem.restaurantservice.domain.food.FoodPersistencePort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

@Service
public class FoodJpaAdapter implements FoodPersistencePort {

    private final FoodRepository foodRepository;

    public FoodJpaAdapter(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public Mono<FoodDto> getFoodById(Long fakeFoodId) {
        return foodRepository.findById(fakeFoodId).map(this::mapToFoodDto);
    }

    protected FoodDto mapToFoodDto(FoodEntity foodEntity) {
        return FoodDto.builder()
                .id(foodEntity.getId())
                .name(foodEntity.getName())
                .vegetable(foodEntity.isVegetable())
                .build();
    }

    protected FoodEntity mapToFoodEntity(FoodDto fakeFoodDto) {
        return FoodEntity.builder()
                .id(fakeFoodDto.getId())
                .name(fakeFoodDto.getName())
                .vegetable(fakeFoodDto.isVegetable())
                .build();
    }


//    @PostConstruct
//    void postconstruct() {
//        FoodEntity foodEntity = FoodEntity.builder().name("asd").vegetable(true).build();
//        Mono<FoodEntity> foodEntityMono = foodRepository.save(foodEntity);
//        System.out.println("done");
//    }
}
