package ru.pizza.restaurant.row_map.order_basket;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import ru.pizza.restaurant.dto.order_basket.OrderBasketContentDTO;
import ru.pizza.restaurant.dto.order_basket.OrderProductContentDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class Receiving
        implements ResultSetExtractor<Map<OrderBasketContentDTO, List<OrderProductContentDTO>>> {

    @Override
    public Map<OrderBasketContentDTO, List<OrderProductContentDTO>> extractData(ResultSet r) throws SQLException, DataAccessException {
        Map<OrderBasketContentDTO, List<OrderProductContentDTO>> receivingMap = new LinkedHashMap<>();
        while (r.next()) {
            OrderBasketContentDTO basket = new OrderBasketContentDTO() {
                {
                    setFio(r.getString("fio"));
                    setAddress(r.getString("address"));
                }
            };
            receivingMap.putIfAbsent(basket, new ArrayList<>());

            receivingMap.get(basket).add(new OrderProductContentDTO() {{
                setTitle(r.getString("title"));
                setCount(r.getInt("count"));
                if (!r.getBoolean("is_ready")) {
                    basket.setReady(false);
                }
            }});
        }

        return receivingMap;
    }
}
