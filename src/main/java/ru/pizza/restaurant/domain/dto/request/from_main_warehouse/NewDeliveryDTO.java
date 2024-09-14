package ru.pizza.restaurant.domain.dto.request.from_main_warehouse;

import lombok.Data;

import java.util.List;

/**
 * {@link NewDeliveryDTO}
 * dto которое хранит данные ресторана и его новых ингредиентов, полученных из мкс main-warehouse
 */
@Data
public class NewDeliveryDTO {
    private BuildingFromMainWarehouseDTO building;
    private List<IngredientFromMainWarehouseDTO> ingredientList;
}
