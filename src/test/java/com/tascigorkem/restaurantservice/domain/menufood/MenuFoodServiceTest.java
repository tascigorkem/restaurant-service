package com.tascigorkem.restaurantservice.domain.menufood;

import com.tascigorkem.restaurantservice.domain.DomainModelFaker;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.Mockito.*;

class MenuFoodServiceTest {

    private final MenuFoodPersistencePort menuFoodPersistencePort = mock(MenuFoodPersistencePort.class);
    private final MenuFoodService subject = new MenuFoodServiceImpl(menuFoodPersistencePort);

    /**
     * Unit test for MenuFoodService:getFoodPriceInfoByMenuId
     */
    @Test
    void testGetFoodPriceInfoByMenuId() {
        // arrange
        UUID fakeMenuFoodId = DomainModelFaker.fakeId();
        MenuFoodDto fakeMenuFoodDto = DomainModelFaker.getFakeMenuFoodDto(fakeMenuFoodId);
        when(menuFoodPersistencePort.getFoodPriceInfoByMenuId(fakeMenuFoodDto.getMenuId(), fakeMenuFoodDto.getFoodId()))
                .thenReturn(Mono.just(fakeMenuFoodDto));

        // act
        Mono<MenuFoodDto> result = subject.getFoodPriceInfoByMenuId(fakeMenuFoodDto.getMenuId(), fakeMenuFoodDto.getFoodId());

        //assert
        StepVerifier.create(result)
                .expectNext(fakeMenuFoodDto)
                .expectComplete()
                .verify();

        verify(menuFoodPersistencePort).getFoodPriceInfoByMenuId(fakeMenuFoodDto.getMenuId(), fakeMenuFoodDto.getFoodId());
    }
}
