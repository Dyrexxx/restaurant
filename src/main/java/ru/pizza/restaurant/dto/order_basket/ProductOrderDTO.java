package ru.pizza.restaurant.dto.order_basket;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductOrderDTO {
    private String title;
    private int count;
    private int buildingId;
}
