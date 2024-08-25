package ru.pizza.restaurant.row_map.new_delivery;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.pizza.restaurant.dto.new_delivery.transfer.IngredientTransferDeliveryDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class GetIngredientDeliveryRowMap implements RowMapper<IngredientTransferDeliveryDTO> {

    @Override
    public IngredientTransferDeliveryDTO mapRow(ResultSet r, int rowNum) throws SQLException {
        return new IngredientTransferDeliveryDTO() {{
            setTitle(r.getString("title"));
            setWeight(r.getInt("weight"));
            setNew(r.getBoolean("is_new"));
        }};
    }
}
