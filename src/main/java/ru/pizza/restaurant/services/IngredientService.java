package ru.pizza.restaurant.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pizza.restaurant.repository.WarehouseRepository;
import ru.pizza.restaurant.entities.Ingredient;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class IngredientService {
    private final WarehouseRepository warehouseRepository;

    @Autowired
    public IngredientService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<Ingredient> get(int id) {
        return warehouseRepository.findAllById(Collections.singleton(id));
    }

    public List<Ingredient> index() {
        return warehouseRepository.findAll();
    }

    @Transactional
    public Ingredient save(Ingredient ingredient) {
        return warehouseRepository.save(ingredient);
    }



}
