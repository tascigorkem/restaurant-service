package com.tascigorkem.restaurantservice.api.food;

import com.github.javafaker.Faker;
import com.tascigorkem.restaurantservice.api.food.FoodControllerResponseDto;
import com.tascigorkem.restaurantservice.domain.food.FoodDto;
import com.tascigorkem.restaurantservice.domain.food.FoodService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebFluxTest
class FoodControllerTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private FoodService foodService;

    private static Faker faker = Faker.instance();

    @Test
    void getFood() {
        // arrange
        UUID id = UUID.randomUUID();
        FoodDto fakeFoodDto = getFakeFoodDto(id);
        when(foodService.getFoodById(id)).thenReturn(Mono.just(fakeFoodDto));

        // act
        client.get().uri("/food/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()

                // assert
                .expectStatus().isOk()
                .expectBody(FoodControllerResponseDto.class)
                .value(dto -> assertAll(
                        () -> assertEquals(dto.getId(), id),
                        () -> assertEquals(dto.getName(), fakeFoodDto.getName()),
                        () -> assertEquals(dto.isVegetable(), fakeFoodDto.isVegetable())
                ));

    }

    private FoodDto getFakeFoodDto(UUID id) {
        return new FoodDto(
                    id,
                    faker.name().firstName(),
                    faker.bool().bool()
            );
    }
}
