package ru.pizza.restaurant.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pizza.restaurant.dao.WarehouseDAO;
import ru.pizza.restaurant.global_parent.BaseMethodsDB;
import ru.pizza.restaurant.entities.Ingredient;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WarehouseService implements BaseMethodsDB<Ingredient, Integer> {
    private final WarehouseDAO warehouseDAO;

    @Override
    public List<Ingredient> findAll() {
        return warehouseDAO.findAll();
    }

    @Override
    public Ingredient findById(Integer id) {
        return warehouseDAO.findById(id);
    }

    @Override
    @Transactional
    public void save(Ingredient ingredient) {
        warehouseDAO.save(ingredient);
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        warehouseDAO.deleteById(id);
    }
}
