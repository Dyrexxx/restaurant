package ru.pizza.restaurant.row_map.new_delivery;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.pizza.restaurant.domain.dto.response.new_delivery.IngredientDeliveryDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GetIngredientDeliveryRowMap implements RowMapper<IngredientDeliveryDTO> {

    @Override
    public IngredientDeliveryDTO mapRow(ResultSet r, int rowNum) throws SQLException {
        return new IngredientDeliveryDTO() {{
            setTitle(r.getString("title"));
            setWeight(r.getInt("weight"));
            setNew(r.getBoolean("is_new"));
        }};
    }
}
