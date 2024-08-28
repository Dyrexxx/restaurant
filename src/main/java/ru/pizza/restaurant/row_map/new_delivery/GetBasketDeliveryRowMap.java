package ru.pizza.restaurant.row_map.new_delivery;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import ru.pizza.restaurant.dto.new_delivery.base.BasketDeliveryDTO;
import ru.pizza.restaurant.dto.new_delivery.base.IngredientDeliveryDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetBasketDeliveryRowMap implements ResultSetExtractor<List<BasketDeliveryDTO>> {

    @Override
    public List<BasketDeliveryDTO> extractData(ResultSet r) throws SQLException, DataAccessException {
        List<BasketDeliveryDTO> basketDeliveryList = new ArrayList<>();
        while (r.next()) {

            BasketDeliveryDTO basketDeliveryDTO = new BasketDeliveryDTO() {{
                setId(r.getString("id"));
            }};

            IngredientDeliveryDTO ingredientDeliveryDTO = new IngredientDeliveryDTO() {{
                setTitle(r.getString("title"));
                setWeight(r.getInt("weight"));
            }};
            //TODO возможный рефакторинг(повторение кода)
            if (!basketDeliveryList.contains(basketDeliveryDTO)) {
                basketDeliveryDTO.getIngredientList().add(ingredientDeliveryDTO);
                basketDeliveryList.add(basketDeliveryDTO);
            } else {
                int index = basketDeliveryList.indexOf(basketDeliveryDTO);
                basketDeliveryList.get(index).getIngredientList().add(ingredientDeliveryDTO);
            }
        }

        return basketDeliveryList;
    }
}