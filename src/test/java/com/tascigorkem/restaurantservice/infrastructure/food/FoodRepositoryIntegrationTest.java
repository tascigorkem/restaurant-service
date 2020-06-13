package com.tascigorkem.restaurantservice.infrastructure.food;

import com.tascigorkem.restaurantservice.domain.DomainModelFaker;
import com.tascigorkem.restaurantservice.domain.food.FoodDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FoodRepositoryIntegrationTest {

    @Autowired
    private FoodRepository foodRepository;

    @Test
    void findById() {
        // arrange
        Long fakeFoodId = DomainModelFaker.fakeFoodId();
        FoodDto fakeFoodDto = DomainModelFaker.getFakeFoodDto(fakeFoodId);

        foodRepository.deleteAll()
                .then(foodRepository.save(FoodEntity.builder()
                        .id(1L)
                        .name(fakeFoodDto.getName())
                        .vegetable(fakeFoodDto.isVegetable())
                        .build()))
                .block();

        // act
        Mono<FoodEntity> result = foodRepository.findById(fakeFoodId);

        // assert
        StepVerifier.create(result)
                .assertNext(foodEntity ->
                        assertThat(foodEntity)
                                .usingRecursiveComparison()
                                .isEqualTo(fakeFoodDto))
                .verifyComplete();
    }

}
