package ru.pizza.restaurant.domain.dto.response.order_basket;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductOrderDTO {
    @Schema(description = "Название продукта")
    private String title;
    @Schema(description = "Количество в заказе")
    private int count;
    @Schema(description = "ID в каком ресторане готовится")
    private int buildingId;
}
