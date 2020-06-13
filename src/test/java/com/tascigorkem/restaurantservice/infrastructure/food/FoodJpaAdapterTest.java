package com.tascigorkem.restaurantservice.infrastructure.food;

import com.tascigorkem.restaurantservice.domain.DomainModelFaker;
import com.tascigorkem.restaurantservice.domain.food.FoodDto;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FoodJpaAdapterTest {

    private final FoodRepository foodRepository = mock(FoodRepository.class);
    private final FoodJpaAdapter subject = new FoodJpaAdapter(foodRepository);

    @Test
    void getFoodById() {
        // arrange
        Long fakeFoodId = DomainModelFaker.fakeFoodId();
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