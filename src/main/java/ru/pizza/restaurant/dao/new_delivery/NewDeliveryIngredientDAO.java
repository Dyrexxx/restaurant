package ru.pizza.restaurant.dao.new_delivery;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.pizza.restaurant.global_parent.BasketMethodsDB;
import ru.pizza.restaurant.dto.new_delivery.transfer.IngredientTransferDeliveryDTO;
import ru.pizza.restaurant.row_map.new_delivery.GetIngredientDeliveryRowMap;

import java.util.List;


@Component
@RequiredArgsConstructor
public class NewDeliveryIngredientDAO implements BasketMethodsDB<IngredientTransferDeliveryDTO, String, String> {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<IngredientTransferDeliveryDTO> findAll() {
        return null;
    }
    @Override
    public List<IngredientTransferDeliveryDTO> findAll(String id) {
        String sql = "select i.title, i.weight, i.is_new, b.building_id from new_delivery_basket b join new_delivery_ingredients i on b.id=i.basket_id where basket_id=?";
        return jdbcTemplate.query(sql, new GetIngredientDeliveryRowMap(), id);
    }

    @Override
    public IngredientTransferDeliveryDTO findById(String id) {
        return null;
    }

    @Override
    public void save(IngredientTransferDeliveryDTO ingredientTransferDeliveryDTO) {

    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public void update(int buildingId, String basketId) {

    }
}