package com.tascigorkem.restaurantservice.domain.food;

import com.tascigorkem.restaurantservice.domain.DomainModelFaker;
import com.tascigorkem.restaurantservice.domain.food.FoodDto;
import com.tascigorkem.restaurantservice.domain.food.FoodPersistencePort;
import com.tascigorkem.restaurantservice.domain.food.FoodService;
import com.tascigorkem.restaurantservice.domain.food.FoodServiceImpl;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FoodServiceTest {

    private final FoodPersistencePort foodPersistencePort = mock(FoodPersistencePort.class);
    private final FoodService subject = new FoodServiceImpl(foodPersistencePort);

    @Test
    void getFoodById() {
        // arrange
        UUID fakeFoodId = DomainModelFaker.fakeFoodId();
        FoodDto fakeFoodDto = DomainModelFaker.getFakeFoodDto(fakeFoodId);
        when(foodPersistencePort.getFoodById(fakeFoodId)).thenReturn(Mono.just(fakeFoodDto));

        // act
        Mono<FoodDto> result = subject.getFoodById(fakeFoodId);

        // assert
        StepVerifier.create(result)
                .expectNext(fakeFoodDto)
                .verifyComplete();
    }
}