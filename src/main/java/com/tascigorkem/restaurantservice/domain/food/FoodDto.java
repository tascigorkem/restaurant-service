package com.tascigorkem.restaurantservice.domain.food;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodDto {
    private Long id;
    private String name;
    private boolean vegetable;
}
