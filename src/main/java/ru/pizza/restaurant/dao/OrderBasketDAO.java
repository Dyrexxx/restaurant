package ru.pizza.restaurant.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.pizza.restaurant.dao.parent.AbstractDAO;
import ru.pizza.restaurant.dao.parent.BaseOperationDB;
import ru.pizza.restaurant.dto.order_basket.OrderBasketContentDTO;
import ru.pizza.restaurant.dto.order_basket.OrderProductContentDTO;
import ru.pizza.restaurant.models.Basket;
import ru.pizza.restaurant.row_map.order_basket.Receiving;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class OrderBasketDAO extends AbstractDAO {

    protected OrderBasketDAO(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    public Map<OrderBasketContentDTO, List<OrderProductContentDTO>> findAll() {
        String sql = "select fio, address, p.title, count, is_ready from order_basket o join order_product p on o.id=p.order_basket_id";
        return getJdbcTemplate().query(sql, new Receiving());
    }

    public void save(Basket basket) {
        String orderId = String.valueOf(UUID.randomUUID());

        getJdbcTemplate().update("insert into order_basket(id, fio, address) VALUES (?, ?, ?)",
                orderId,
                basket.getFio(),
                basket.getAddress());

        for (Basket.BasketItem product : basket.getBasketViewItemList()) {
            String productId = String.valueOf(UUID.randomUUID());

            getJdbcTemplate().update("insert into order_product(id, title, count, building_id, order_basket_id) VALUES (?,?, ?, ?, ?)",
                    productId,
                    product.getProductName(),
                    product.getCount(),
                    product.getBuildingId(),
                    orderId);
        }
    }

    public Map findAllById(int id) {
        String sql = "select fio, address, p.title, count, is_ready from order_basket o join order_product p on o.id=p.order_basket_id where p.building_id=?";
        return getJdbcTemplate().query(sql, new Receiving(), id);
    }

    public Object findOneById(Object id) {
        return null;
    }


    public Map<OrderBasketContentDTO, List<OrderProductContentDTO>> findById(int id) {
        String sql = "select fio, address, p.title, count, is_ready from order_basket o join order_product p on o.id=p.order_basket_id where p.building_id=?";
        return getJdbcTemplate().query(sql, new Receiving(), id);
    }

    public void doneOrder(int buildingId, String orderId) {
        getJdbcTemplate().update("update order_product set is_ready = true where order_basket_id=? and building_id=?",
                orderId, buildingId);
    }


}
