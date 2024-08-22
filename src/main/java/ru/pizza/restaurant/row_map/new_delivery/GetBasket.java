package ru.pizza.restaurant.row_map.new_delivery;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.pizza.restaurant.dto.new_delivery.Basket;
import ru.pizza.restaurant.dto.new_delivery.IngredientDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GetBasket implements ResultSetExtractor<Map<Basket, List<IngredientDTO>>> {

    @Override
    public Map<Basket, List<IngredientDTO>> extractData(ResultSet r) throws SQLException, DataAccessException {
        Map<Basket, List<IngredientDTO>> map = new LinkedHashMap<>();
        while (r.next()) {
            Basket basket;

            map.putIfAbsent(basket = new Basket() {{
                setId(r.getString("id"));
            }}, new ArrayList<>());

            map.get(basket).add(new IngredientDTO() {{
                setTitle(r.getString("title"));
                setWeight(r.getInt("weight"));
            }});
        }

        return map;
    }
}
