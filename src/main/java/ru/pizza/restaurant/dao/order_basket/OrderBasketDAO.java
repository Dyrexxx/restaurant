package ru.pizza.restaurant.dao.order_basket;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.pizza.restaurant.global_parent.BasketMethodsDB;
import ru.pizza.restaurant.dto.order_basket.BasketOrderDTO;
import ru.pizza.restaurant.dto.order_basket.ProductOrderDTO;
import ru.pizza.restaurant.row_map.order_basket.GetBasketOrderRowMap;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderBasketDAO implements BasketMethodsDB<BasketOrderDTO, String, Integer> {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<BasketOrderDTO> findAll() {
        String sql = "select o.id, o.fio, o.address,p.count, p.title, p.count from order_basket o join order_product p on o.id=p.order_basket_id";
        return jdbcTemplate.query(sql, new GetBasketOrderRowMap());
    }
    @Override
    public List<BasketOrderDTO> findAll(Integer id) {
        String sql = "select o.id, o.fio, o.address, p.count, p.title, p.building_id from order_basket o join order_product p on o.id=p.order_basket_id where p.building_id=?";
        return jdbcTemplate.query(sql, new GetBasketOrderRowMap(), id);
    }

    @Override
    public void update(int buildingId, String id) {
        jdbcTemplate.update("update order_product set is_ready = true where order_basket_id=? and building_id=?",
                id, buildingId);
    }

    @Override
    public BasketOrderDTO findById(String id) {
        return null;
    }

    @Override
    public void save(BasketOrderDTO basketOrderDTO) {
        String orderId = String.valueOf(UUID.randomUUID());

        jdbcTemplate.update("insert into order_basket(id, fio, address) VALUES (?, ?, ?)",
                orderId,
                basketOrderDTO.getFio(),
                basketOrderDTO.getAddress());

        for (ProductOrderDTO product : basketOrderDTO.getProductsList()) {
            String productId = String.valueOf(UUID.randomUUID());

            jdbcTemplate.update("insert into order_product(id, title, count, building_id, order_basket_id) VALUES (?, ?, ?, ?, ?)",
                    productId,
                    product.getTitle(),
                    product.getCount(),
                    product.getBuildingId(),
                    orderId);
        }

    }
    @Override
    public void deleteById(String id) {

    }
}
