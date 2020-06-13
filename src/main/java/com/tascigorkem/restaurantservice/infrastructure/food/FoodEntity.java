package com.tascigorkem.restaurantservice.infrastructure.food;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("food")
public class FoodEntity implements Persistable<Long> {

    @Id
    @Column("id")
    private Long id;

    @Column("name")
    private String name;

    @Column("vegetable")
    private boolean vegetable;

    @Override
    public boolean isNew() {
        return true;
    }
}
