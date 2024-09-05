package ru.pizza.restaurant.services.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pizza.restaurant.dao.restaurant.WarehouseDAO;
import ru.pizza.restaurant.domain.entities.Ingredient;
import ru.pizza.restaurant.services.BaseMethodsService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RestWarehouseService implements BaseMethodsService<Ingredient, Integer> {
    private final WarehouseDAO warehouseDAO;

    /**
     *
     * @return Возвращает все склады всех ресторанов
     */
    @Override
    public List<Ingredient> index() {
        return warehouseDAO.findAll();
    }
}
