package com.tascigorkem.restaurantservice.api;

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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebFluxTest
public class FoodControllerTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private FoodService foodService;

    private static Faker faker = Faker.instance();

    @Test
    void getFood() {
        Long id = 1L;
        FoodDto fakeFoodDto = getFakeFoodDto(id);
        when(foodService.getFoodById(id)).thenReturn(Mono.just(fakeFoodDto));

        client.get().uri("/food/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                // assert
                .expectStatus().isOk()
                .expectBody(FoodControllerResponseDto.class)
                .value(dto -> assertAll(
                        () -> assertEquals(dto.getId(), id),
                        () -> assertEquals(dto.getName(), fakeFoodDto.getName()),
                        () -> assertEquals(fakeFoodDto.isVegetable(), fakeFoodDto.isVegetable())
                ));

    }

    private FoodDto getFakeFoodDto(Long id) {
        return new FoodDto(
                    id,
                    faker.name().firstName(),
                    faker.bool().bool()
            );
    }
}
