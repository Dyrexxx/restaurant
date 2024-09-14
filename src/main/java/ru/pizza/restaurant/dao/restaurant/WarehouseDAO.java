package ru.pizza.restaurant.dao.restaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.pizza.restaurant.domain.entities.Ingredient;
import ru.pizza.restaurant.repositories.WarehouseRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WarehouseDAO{
    private final WarehouseRepository warehouseRepository;

    public List<Ingredient> findAll() {
        return warehouseRepository.findAll();
    }
}
