package ru.pizza.restaurant.domain.dto.request.from_main_warehouse;

import lombok.Data;

/**
 * {@link IngredientFromMainWarehouseDTO}
 * dto для получения новых ингредиентов из мкс main-warehouse
 */
@Data
public class IngredientFromMainWarehouseDTO {
    private String title;
    private int weight;
    private boolean isNew;
}
