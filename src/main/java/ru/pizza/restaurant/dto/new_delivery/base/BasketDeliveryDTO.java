package ru.pizza.restaurant.dto.new_delivery.base;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class BasketDeliveryDTO {
    private String id;
    private List<IngredientDeliveryDTO> ingredientList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasketDeliveryDTO that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}