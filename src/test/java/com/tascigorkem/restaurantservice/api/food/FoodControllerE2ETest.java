package com.tascigorkem.restaurantservice.api.food;

import com.github.javafaker.Faker;
import com.tascigorkem.restaurantservice.domain.DomainModelFaker;
import com.tascigorkem.restaurantservice.domain.food.FoodDto;
import com.tascigorkem.restaurantservice.infrastructure.base.Status;
import com.tascigorkem.restaurantservice.infrastructure.food.FoodEntity;
import com.tascigorkem.restaurantservice.infrastructure.food.FoodRepository;
import com.tascigorkem.restaurantservice.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FoodControllerE2ETest {

    private static Faker faker = Faker.instance();
    @Autowired
    private ApplicationContext context;
    @Autowired
    private FoodRepository foodRepository;

    @Test
    void getFood() {
        // arrange
        final WebTestClient client = WebTestClient.bindToApplicationContext(context).build();

        UUID fakeFoodId = UUID.randomUUID();
        FoodDto fakeFoodDto = DomainModelFaker.getFakeFoodDto(fakeFoodId);

        LocalDateTime now = DateUtil.getInstance().convertToLocalDateTime(new Date());

        // prepare db, delete all entities and insert one entity
        foodRepository.deleteAll()
                .then(foodRepository.save(FoodEntity.builder()
                        .id(fakeFoodId)
                        .creationTime(now)
                        .updateTime(now)
                        .status(Status.CREATED)
                        .deleted(false)
                        .name(fakeFoodDto.getName())
                        .vegetable(fakeFoodDto.isVegetable())
                        .build()))
                .block();

        // act
        client.get().uri("/foods/" + fakeFoodId)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()

                // assert
                .expectStatus().isOk()
                .expectBody(FoodControllerResponseDto.class)
                .value(dto -> assertAll(
                        () -> assertEquals(dto.getId(), fakeFoodId),
                        () -> assertEquals(dto.getName(), fakeFoodDto.getName()),
                        () -> assertEquals(dto.isVegetable(), fakeFoodDto.isVegetable())
                ));

    }
}

