package ru.pizza.restaurant.dao.restaurant;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.pizza.restaurant.domain.entities.Ingredient;
import ru.pizza.restaurant.repositories.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WarehouseDAO {
    private final WarehouseRepository warehouseRepository;

    public List<Ingredient> findAll() {
        List<Ingredient> ingredients = warehouseRepository.findAll();
        if (ingredients.isEmpty()) {
            throw new RuntimeException("Склады не найдены");
        }
        return ingredients;
    }
}
