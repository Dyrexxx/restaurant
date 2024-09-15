package ru.pizza.restaurant.domain.dto.batch_update.order_basket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderBatchDTO {
    private String id;
    private String title;
    private int count;
    private int buildingId;
}
