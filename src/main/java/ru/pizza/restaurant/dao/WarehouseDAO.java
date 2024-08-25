package ru.pizza.restaurant.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.pizza.restaurant.entities.Ingredient;
import ru.pizza.restaurant.global_parent.BaseMethodsDB;
import ru.pizza.restaurant.repositories.WarehouseRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WarehouseDAO implements BaseMethodsDB<Ingredient, Integer> {
    private final WarehouseRepository warehouseRepository;
    @Override
    public List<Ingredient> findAll() {
        return warehouseRepository.findAll();
    }

    @Override
    public Ingredient findById(Integer id) {
        return warehouseRepository.findById(id).get();
    }

    @Override
    public void save(Ingredient ingredient) {
        warehouseRepository.save(ingredient);
    }

    @Override
    public void deleteById(Integer id) {
        warehouseRepository.deleteById(id);
    }
}
