package ru.pizza.restaurant.row_map.new_delivery;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.pizza.restaurant.dto.new_delivery.AcceptIngredients;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GetForAcceptIngredient implements RowMapper<AcceptIngredients> {
    public static final String GET_INGREDIENT_SQL = "select title, weight, is_new, b.building_id from new_delivery_basket b " +
            "join new_delivery_ingredients i on b.id=i.basket_id where basket_id=?";

    @Override
    public AcceptIngredients mapRow(ResultSet r, int rowNum) throws SQLException {
        return new AcceptIngredients() {{
            setTitle(r.getString("title"));
            setWeight(r.getInt("weight"));
            setNew(r.getBoolean("is_new"));
        }};
    }
}
