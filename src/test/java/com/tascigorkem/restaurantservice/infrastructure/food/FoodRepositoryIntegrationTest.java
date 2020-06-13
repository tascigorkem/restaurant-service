package com.tascigorkem.restaurantservice.infrastructure.food;

import com.tascigorkem.restaurantservice.domain.DomainModelFaker;
import com.tascigorkem.restaurantservice.domain.food.FoodDto;
import com.tascigorkem.restaurantservice.infrastructure.base.Status;
import com.tascigorkem.restaurantservice.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FoodRepositoryIntegrationTest {

    @Autowired
    private FoodRepository foodRepository;

    @Test
    void findById() {
        // arrange
        UUID fakeFoodId = DomainModelFaker.fakeFoodId();
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
        Mono<FoodEntity> result = foodRepository.findById(fakeFoodId);
        FoodEntity foodEntity = result.block();

        // assert
        assertAll(
                () -> assertEquals(fakeFoodDto.getId(), foodEntity.getId()),
                () -> assertEquals(fakeFoodDto.getName(), foodEntity.getName()),
                () -> assertEquals(fakeFoodDto.isVegetable(), foodEntity.isVegetable())
        );
    }


    @Test
    void givenFoodId_whenCreateFoodTwiceSameId_thenFails() {
        // arrange
        UUID fakeFoodId = DomainModelFaker.fakeFoodId();
        FoodDto fakeFoodDto = DomainModelFaker.getFakeFoodDto(fakeFoodId);
        LocalDateTime now = DateUtil.getInstance().convertToLocalDateTime(new Date());

        // prepare db, insert first entity
        foodRepository.save(FoodEntity.builder()
                .id(fakeFoodId)
                .creationTime(now)
                .updateTime(now)
                .status(Status.CREATED)
                .deleted(false)
                .name(fakeFoodDto.getName())
                .vegetable(fakeFoodDto.isVegetable())
                .build()).block();

        // act
        // insert second entity
        Executable createFood = () ->
                foodRepository.save(FoodEntity.builder()
                        .id(fakeFoodId)
                        .creationTime(now)
                        .updateTime(now)
                        .status(Status.CREATED)
                        .deleted(false)
                        .name(fakeFoodDto.getName())
                        .vegetable(fakeFoodDto.isVegetable())
                        .build()).block();

        // assert
        assertThrows(DataIntegrityViolationException.class, createFood);

    }

}
