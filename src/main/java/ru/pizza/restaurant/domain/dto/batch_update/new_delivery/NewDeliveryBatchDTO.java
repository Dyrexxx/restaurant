package ru.pizza.restaurant.domain.dto.batch_update.new_delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewDeliveryBatchDTO {
    private int id;
    private String basketId;
}
