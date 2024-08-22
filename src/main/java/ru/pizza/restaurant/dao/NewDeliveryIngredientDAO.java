package ru.pizza.restaurant.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.pizza.restaurant.dao.parent.AbstractDAO;
import ru.pizza.restaurant.dao.parent.BaseOperationDB;
import ru.pizza.restaurant.dto.new_delivery.AcceptIngredients;
import ru.pizza.restaurant.row_map.new_delivery.GetForAcceptIngredient;

import java.util.List;


@Component
public class NewDeliveryIngredientDAO extends AbstractDAO implements BaseOperationDB<List, String> {

    protected NewDeliveryIngredientDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public List findAll() {
        return null;
    }


    @Override
    public List<AcceptIngredients> findOneById(String basketId) {
        return  getJdbcTemplate().query(GetForAcceptIngredient.GET_INGREDIENT_SQL,
                new GetForAcceptIngredient(), basketId);
    }
}