package com.tascigorkem.restaurantservice.api.food;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tascigorkem.restaurantservice.api.ApiModelFaker;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebFluxTest
class FoodControllerTest {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private WebTestClient client;

    @MockBean
    private FoodService foodService;

    private FoodController subject = new FoodControllerImpl(foodService);

    @Test
    void testGetAllFoods() {
        // test endpoint FoodController:getFoods
        // arrange
        FoodDto fakeFoodDto = DomainModelFaker.getFakeFoodDto(DomainModelFaker.fakeFoodId());
        List<FoodDto> foodDtoList = Arrays.asList(fakeFoodDto, fakeFoodDto, fakeFoodDto);
        when(foodService.getAllFoods()).thenReturn(Flux.fromIterable(foodDtoList));

        // act
        client.get().uri("/foods")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()

                // assert
                .expectStatus().isOk()
                .expectBody(Response.class)
                .value(response -> {

                    assertAll(
                            () -> assertEquals(HttpStatus.OK, response.getStatus()),
                            () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode())
                    );

                    List<FoodControllerResponseDto> responseDtoList = Arrays.asList(
                            objectMapper.convertValue(response.getPayload(), FoodControllerResponseDto[].class));

                    assertEquals(3, responseDtoList.size());

                    responseDtoList.forEach(responseDto ->
                            assertAll(
                                    () -> assertEquals(fakeFoodDto.getId(), responseDto.getId()),
                                    () -> assertEquals(fakeFoodDto.getName(), responseDto.getName()),
                                    () -> assertEquals(fakeFoodDto.isVegetable(), responseDto.isVegetable())
                            ));

                });
        verify(foodService).getAllFoods();
    }

    @Test
    void givenFoodId_whenGetFood_andFoodExists_thenReturnFood() {
        // test endpoint FoodController:getFoodById
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
                            () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode()),
                            () -> assertEquals(fakeFoodId, foodResponseDto.getId()),
                            () -> assertEquals(fakeFoodDto.getName(), foodResponseDto.getName()),
                            () -> assertEquals(fakeFoodDto.isVegetable(), foodResponseDto.isVegetable())
                    );
                });
        verify(foodService).getFoodById(fakeFoodId);
    }

    @Test
    void givenFoodId_whenGetFood_andFoodNotExists_thenThrowsNotFound() {
        // test endpoint FoodController:getFoodById
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
                                () -> assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode()),
                                () -> assertEquals(new FoodNotFoundException("id", fakeFoodId.toString()).getMessage(), response.getPayload())
                        )
                );
        verify(foodService).getFoodById(fakeFoodId);
    }

    @Test
    void givenFoodControllerRequestDto_whenCreateFood_thenReturnSuccessful_andReturnFood() {
        // test endpoint FoodController:addFood
        // arrange
        FoodControllerRequestDto fakeFoodControllerRequestDto = ApiModelFaker.getFoodControllerRequestDto();
        FoodDto fakeFoodDto = subject.mapToFoodDto().apply(fakeFoodControllerRequestDto);
        when(foodService.addFood(fakeFoodDto)).thenReturn(Mono.just(fakeFoodDto));
        // act
        client.post().uri("/foods")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(fakeFoodControllerRequestDto), FoodControllerRequestDto.class)
                .exchange()

                // assert
                .expectStatus().isOk()
                .expectBody(Response.class)
                .value(response -> {
                            FoodControllerResponseDto foodResponseDto = objectMapper
                                    .convertValue(response.getPayload(), FoodControllerResponseDto.class);
                            assertAll(
                                    () -> assertEquals(HttpStatus.OK, response.getStatus()),
                                    () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode()),
                                    () -> assertEquals(fakeFoodDto.getName(), foodResponseDto.getName()),
                                    () -> assertEquals(fakeFoodDto.isVegetable(), foodResponseDto.isVegetable())
                            );
                        }

                );
        verify(foodService).addFood(fakeFoodDto);
    }

    @Test
    void givenFoodId_andFoodControllerRequestDto_whenUpdateFood_andExists_thenReturnSuccessful() {
        // test endpoint FoodController:updateFood
        // arrange
        FoodControllerRequestDto fakeFoodControllerRequestDto = ApiModelFaker.getFoodControllerRequestDto();
        FoodDto fakeFoodDto = subject.mapToFoodDto().apply(fakeFoodControllerRequestDto);
        // act
        client.put().uri("/foods")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(fakeFoodControllerRequestDto), FoodControllerRequestDto.class)
                .exchange()

                // assert
                .expectStatus().isOk()
                .expectBody(Response.class)
                .value(response -> {
                            assertAll(
                                    () -> assertEquals(HttpStatus.OK, response.getStatus()),
                                    () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode())
                            );
                        }

                );
        verify(foodService).updateFood(fakeFoodDto);
    }

    @Test
    void givenFoodId_whenRemoveFood_andExists_thenReturnSuccessful() {
        // test endpoint FoodController:removeFood
        // arrange
        UUID fakeFoodId = UUID.randomUUID();
        // act
        client.delete().uri("/foods/" + fakeFoodId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()

                // assert
                .expectStatus().isOk()
                .expectBody(Response.class)
                .value(response -> {
                            assertAll(
                                    () -> assertEquals(HttpStatus.OK, response.getStatus()),
                                    () -> assertEquals(HttpStatus.OK.value(), response.getStatusCode())
                            );
                        }

                );
        verify(foodService).removeFood(fakeFoodId);
    }
}
