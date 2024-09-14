package ru.pizza.restaurant.dao.new_delivery;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.pizza.restaurant.domain.dto.new_delivery.IngredientDeliveryDTO;
import ru.pizza.restaurant.row_map.new_delivery.GetIngredientDeliveryRowMap;

import java.util.List;


@Component
@RequiredArgsConstructor
public class NewDeliveryIngredientDAO{
    private final JdbcTemplate jdbcTemplate;

    /***
     * Возвращает нужную корзину доставки
     *
     * @param id ID корзины
     * @return Возвращает нужную корзину
     */
    public List<IngredientDeliveryDTO> findAll(String id) {
        String sql = "select i.title, i.weight, i.is_new, b.building_id from new_delivery_basket b join new_delivery_ingredients i on b.id=i.basket_id where basket_id=?";
        return jdbcTemplate.query(sql, new GetIngredientDeliveryRowMap(), id);
    }
}