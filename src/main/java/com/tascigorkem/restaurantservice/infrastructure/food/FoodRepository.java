package com.tascigorkem.restaurantservice.infrastructure.food;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends ReactiveCrudRepository<FoodEntity, Long> {

}
