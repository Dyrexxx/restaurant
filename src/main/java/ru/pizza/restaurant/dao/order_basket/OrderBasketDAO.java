package ru.pizza.restaurant.dao.order_basket;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.pizza.restaurant.domain.dto.order_basket.BasketOrderDTO;
import ru.pizza.restaurant.domain.dto.order_basket.ProductOrderDTO;
import ru.pizza.restaurant.row_map.order_basket.GetBasketOrderRowMap;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrderBasketDAO {
    private final JdbcTemplate jdbcTemplate;

    /***
     * Возвращает все онлайн-заказы из всех ресторанов
     * @return Возвращает все онлайн-заказы
     */
    public List<BasketOrderDTO> findAll() {
        String sql = "select o.id, o.fio, o.address,p.count, p.title, p.count from order_basket o join order_product p on o.id=p.order_basket_id";
        return jdbcTemplate.query(sql, new GetBasketOrderRowMap());
    }

    /***
     * Возвращает все онлайн-заказы ресторана по его ID
     *
     * @param id ID ресторана
     * @return Онлайн-заказы для него
     */
    public List<BasketOrderDTO> findAll(Integer id) {
        String sql = "select o.id, o.fio, o.address, p.count, p.title, p.building_id from order_basket o join order_product p on o.id=p.order_basket_id where p.building_id=?";
        return jdbcTemplate.query(sql, new GetBasketOrderRowMap(), id);
    }

    public void update(int buildingId, String id) {
        jdbcTemplate.update("update order_product set is_ready = true where order_basket_id=? and building_id=?",
                id, buildingId);
    }

    /***
     * Сохраняет онлайн-заказ пользователя. Создает корзину и добавляет в нее онлайн-заказ.
     * Сделано это для того, чтобы распределить корзину онлайн-заказа по ресторанам.
     *
     * @param basketOrderDTO онлайн-заказ
     */
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
}
