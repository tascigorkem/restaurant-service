package com.tascigorkem.restaurantservice.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodControllerResponseDto {

    private Long id;
    private String name;
    private String type;

}
