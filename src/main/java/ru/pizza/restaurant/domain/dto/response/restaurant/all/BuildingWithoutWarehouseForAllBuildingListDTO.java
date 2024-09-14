package ru.pizza.restaurant.domain.dto.response.restaurant.all;

import lombok.Data;

/**
 * dto для передачи данных для главной страницы выбора ресторана
 */
@Data
public class BuildingWithoutWarehouseForAllBuildingListDTO {
    private int id;
    private String title;
    private String address;
}
