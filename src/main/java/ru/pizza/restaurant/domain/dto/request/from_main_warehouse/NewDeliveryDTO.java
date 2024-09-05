package ru.pizza.restaurant.domain.dto.request.from_main_warehouse;

import lombok.Data;

import java.util.List;

@Data
public class NewDeliveryDTO {
    private List<ItemDelivery> delivery;

    @Data
    public static class ItemDelivery {
        private BuildingFromMainWarehouseDTO building;
        private List<IngredientFromMainWarehouseDTO> ingredientList;
    }
}
