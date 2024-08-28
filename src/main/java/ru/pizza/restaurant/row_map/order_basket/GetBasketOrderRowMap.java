package ru.pizza.restaurant.row_map.order_basket;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import ru.pizza.restaurant.dto.order_basket.BasketOrderDTO;
import ru.pizza.restaurant.dto.order_basket.ProductOrderDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;


@Component
public class GetBasketOrderRowMap
        implements ResultSetExtractor<List<BasketOrderDTO>> {

    @Override
    public List<BasketOrderDTO> extractData(ResultSet r) throws SQLException, DataAccessException {
        List<BasketOrderDTO> basketOrderList = new ArrayList<>();
        while (r.next()) {
            BasketOrderDTO basket = new BasketOrderDTO() {
                {
                    setId(r.getString("id"));
                    setFio(r.getString("fio"));
                    setAddress(r.getString("address"));
                }
            };
            ProductOrderDTO product = new ProductOrderDTO() {{
                setTitle(r.getString("title"));
                setCount(r.getInt("count"));
                setBuildingId(r.getInt("building_id"));
            }};

            if (!basketOrderList.contains(basket)) {
                basket.getProductsList().add(product);
                basketOrderList.add(basket);
            } else {
                int index = basketOrderList.indexOf(basket);
                basketOrderList.get(index).getProductsList().add(product);
            }
        }

        return basketOrderList;
    }
}
