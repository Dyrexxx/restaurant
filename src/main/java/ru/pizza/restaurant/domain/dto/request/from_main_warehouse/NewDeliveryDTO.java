package ru.pizza.restaurant.domain.dto.request.from_main_warehouse;

import lombok.Data;

import java.util.List;

@Data
public class NewDeliveryDTO {
    private BuildingFromMainWarehouseDTO building;
    private List<IngredientFromMainWarehouseDTO> ingredientList;
}
