package com.tascigorkem.restaurantservice.infrastructure.food;

import com.tascigorkem.restaurantservice.infrastructure.base.BaseEntity;
import com.tascigorkem.restaurantservice.infrastructure.base.Status;
import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Table("food")
public class FoodEntity extends BaseEntity {

    @Builder
    public FoodEntity(LocalDateTime creationTime, LocalDateTime updateTime, Status status, boolean deleted, LocalDateTime deletionTime, UUID id, String name, boolean vegetable) {
        super(creationTime, updateTime, status, deletionTime, deleted, id);
        this.name = name;
        this.vegetable = vegetable;
    }

    @Column("name")
    private String name;

    @Column("vegetable")
    private boolean vegetable;

}
