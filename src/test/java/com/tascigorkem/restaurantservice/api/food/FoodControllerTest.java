package com.tascigorkem.restaurantservice.api.food;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.tascigorkem.restaurantservice.api.response.Response;
import com.tascigorkem.restaurantservice.domain.DomainModelFaker;
import com.tascigorkem.restaurantservice.domain.exception.FoodNotFoundException;
import com.tascigorkem.restaurantservice.domain.food.FoodDto;
import com.tascigorkem.restaurantservice.domain.food.FoodService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebFluxTest
class FoodControllerTest {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WebTestClient client;

    @MockBean
    private FoodService foodService;

    @Test
    void givenFoodId_whenFoodExists_thenReturnFood() {
        // arrange
        UUID fakeFoodId = UUID.randomUUID();
        FoodDto fakeFoodDto = DomainModelFaker.getFakeFoodDto(fakeFoodId);
        when(foodService.getFoodById(fakeFoodId)).thenReturn(Mono.just(fakeFoodDto));
        // act
        client.get().uri("/foods/" + fakeFoodId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()

                // assert
                .expectStatus().isOk()
                .expectBody(Response.class)
                .value(response -> {
                    FoodControllerResponseDto foodResponseDto = objectMapper
                            .convertValue(response.getPayload(), FoodControllerResponseDto.class);

                    assertAll(
                            () -> assertEquals(HttpStatus.OK, response.getStatus()),
                            () -> assertEquals(fakeFoodId, foodResponseDto.getId()),
                            () -> assertEquals(fakeFoodDto.getName(), foodResponseDto.getName()),
                            () -> assertEquals(fakeFoodDto.isVegetable(), foodResponseDto.isVegetable())
                    );
                });
    }

    @Test
    void givenFoodId_whenFoodNotExists_thenThrowsNotFound() {
        // arrange
        UUID fakeFoodId = UUID.randomUUID();
        when(foodService.getFoodById(fakeFoodId)).thenReturn(Mono.empty());
        // act
        client.get().uri("/foods/" + fakeFoodId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()

                // assert
                .expectStatus().isOk()
                .expectBody(Response.class)
                .value(response ->
                        assertAll(
                                () -> assertEquals(HttpStatus.NOT_FOUND, response.getStatus()),
                                () -> assertEquals(new FoodNotFoundException("id", fakeFoodId.toString()).getMessage(), response.getPayload())
                        )
                );
    }

    @Test
    void givenFoodControllerRequestDto_whenNotExists_andCreateFood_thenReturnSuccessful_andReturnFood() {
        // arrange
        // act
        // assert
    }

    @Test
    void givenFoodControllerRequestDto_whenExists_andCreateFood_thenThrowsDublicateFound() {
        // arrange
        // act
        // assert
    }

    @Test
    void givenFoodId_andFoodControllerRequestDto_whenExists_andUpdateFood_thenReturnSuccessful() {
        // arrange
        // act
        // assert
    }

    @Test
    void givenFoodId_whenExists_andDeleteFood_thenReturnSuccessful() {
        // arrange
        // act
        // assert
    }
}
