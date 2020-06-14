package com.tascigorkem.restaurantservice.api.food;

import com.github.javafaker.Faker;
import com.tascigorkem.restaurantservice.domain.DomainModelFaker;
import com.tascigorkem.restaurantservice.domain.food.FoodDto;
import com.tascigorkem.restaurantservice.domain.food.FoodPersistencePort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class FoodControllerIntegrationTest {

    private static Faker faker = Faker.instance();
    @Autowired
    private ApplicationContext context;
    @MockBean
    private FoodPersistencePort foodPersistencePort;

    @Test
    void getFood() {
        // arrange
        final WebTestClient client = WebTestClient.bindToApplicationContext(context).build();

        UUID id = UUID.randomUUID();
        FoodDto fakeFoodDto = DomainModelFaker.getFakeFoodDto(id);
        when(foodPersistencePort.getFoodById(id)).thenReturn(Mono.just(fakeFoodDto));

        // act
        client.get().uri("/foods/" + id)
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
}
