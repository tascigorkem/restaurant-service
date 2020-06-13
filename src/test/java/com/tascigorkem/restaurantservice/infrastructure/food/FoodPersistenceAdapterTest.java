package com.tascigorkem.restaurantservice.infrastructure.food;

import com.tascigorkem.restaurantservice.domain.DomainModelFaker;
import com.tascigorkem.restaurantservice.domain.food.FoodDto;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FoodPersistenceAdapterTest {

    private final FoodRepository foodRepository = mock(FoodRepository.class);
    private final FoodPersistenceAdapter subject = new FoodPersistenceAdapter(foodRepository);

    @Test
    void getFoodById() {
        // arrange
        UUID fakeFoodId = DomainModelFaker.fakeFoodId();
        FoodDto fakeFoodDto = DomainModelFaker.getFakeFoodDto(fakeFoodId);
        FoodEntity fakeFoodEntity = subject.mapToFoodEntity(fakeFoodDto);
        when(foodRepository.findById(fakeFoodId)).thenReturn(Mono.just(fakeFoodEntity));

        // act
        Mono<FoodDto> result = subject.getFoodById(fakeFoodId);

        // assert
        StepVerifier.create(result)
                .assertNext(foodEntity ->
                        assertThat(foodEntity)
                                .usingRecursiveComparison()
                                .isEqualTo(fakeFoodDto))
                .verifyComplete();
    }
}