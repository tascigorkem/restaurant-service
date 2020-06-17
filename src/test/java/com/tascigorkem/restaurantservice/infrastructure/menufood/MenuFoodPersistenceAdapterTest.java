package com.tascigorkem.restaurantservice.infrastructure.menufood;

import com.tascigorkem.restaurantservice.domain.DomainModelFaker;
import com.tascigorkem.restaurantservice.domain.food.FoodDto;
import com.tascigorkem.restaurantservice.domain.menufood.MenuFoodDto;
import com.tascigorkem.restaurantservice.infrastructure.base.Status;
import com.tascigorkem.restaurantservice.infrastructure.food.FoodEntity;
import com.tascigorkem.restaurantservice.infrastructure.food.FoodRepository;
import com.tascigorkem.restaurantservice.util.DateUtil;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MenuFoodPersistenceAdapterTest {

    private final MenuFoodRepository menuFoodRepository = mock(MenuFoodRepository.class);
    private final FoodRepository foodRepository = mock(FoodRepository.class);
    private final MenuFoodPersistenceAdapter subject = new MenuFoodPersistenceAdapter(foodRepository, menuFoodRepository);

    /**
     * Unit test for MenuFoodPersistenceAdapter:getMenuFoodById
     */
    @Test
    void getMenuFoodById() {
        // arrange
        UUID fakeMenuFoodId = DomainModelFaker.fakeId();
        MenuFoodDto fakeMenuFoodDto = DomainModelFaker.getFakeMenuFoodDto(fakeMenuFoodId);
        MenuFoodEntity fakeMenuFoodEntity = subject.mapToMenuFoodEntity(fakeMenuFoodDto);

        FoodDto fakeFoodDto = DomainModelFaker.getFakeFoodDto(fakeMenuFoodDto.getFoodId());

        LocalDateTime now = DateUtil.getInstance().convertToLocalDateTime(new Date());

        FoodEntity fakeFoodEntity = FoodEntity.builder()
                .id(fakeFoodDto.getId())
                .creationTime(now)
                .updateTime(now)
                .status(Status.CREATED)
                .deleted(false)
                .name(fakeFoodDto.getName())
                .vegetable(fakeFoodDto.isVegetable())
                .price(fakeFoodDto.getPrice())
                .build();

        when(menuFoodRepository.findAllByMenuIdAndFoodId(fakeMenuFoodDto.getMenuId(), fakeMenuFoodDto.getFoodId()))
                .thenReturn(Mono.just(fakeMenuFoodEntity));
        when(foodRepository.findById(fakeMenuFoodEntity.getFoodId())).thenReturn(Mono.just(fakeFoodEntity));

        // act
        Mono<MenuFoodDto> result = subject.getFoodPriceInfoByMenuId(fakeMenuFoodDto.getMenuId(), fakeMenuFoodDto.getFoodId());
        MenuFoodDto menuFoodDtoResult = result.block();

        // assert

        // assert
        assertAll(
                () -> assertEquals(fakeMenuFoodDto.getId(), menuFoodDtoResult.getId()),
                () -> assertEquals(fakeMenuFoodDto.isExtended(), menuFoodDtoResult.isExtended()),
                () -> assertEquals(fakeMenuFoodDto.getExtendedPrice(), menuFoodDtoResult.getExtendedPrice()),
                () -> assertEquals(fakeMenuFoodDto.getMenuId(), menuFoodDtoResult.getMenuId()),
                () -> assertEquals(fakeMenuFoodDto.getFoodId(), menuFoodDtoResult.getFoodId()),
                () -> assertEquals(fakeFoodDto.getPrice(), menuFoodDtoResult.getOriginalPrice()),
                () -> assertEquals(fakeFoodDto.getName(), menuFoodDtoResult.getFoodName())
        );
    }

}