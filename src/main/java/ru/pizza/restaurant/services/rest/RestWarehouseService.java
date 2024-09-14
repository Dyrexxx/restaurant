package ru.pizza.restaurant.services.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pizza.restaurant.dao.restaurant.WarehouseDAO;
import ru.pizza.restaurant.domain.entities.Ingredient;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RestWarehouseService{
    private final WarehouseDAO warehouseDAO;

    /**
     * @return Возвращает все склады всех ресторанов
     */
    public List<Ingredient> index() {
        return warehouseDAO.findAll();
    }
}
