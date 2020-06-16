package com.tascigorkem.restaurantservice.api.food;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FoodControllerRequestDto {

    private String name;
    private boolean vegetable;
}
