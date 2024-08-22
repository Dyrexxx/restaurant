package ru.pizza.restaurant.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.pizza.restaurant.dao.parent.AbstractDAO;
import ru.pizza.restaurant.dao.parent.BaseOperationDB;
import ru.pizza.restaurant.dto.new_delivery.AcceptIngredients;
import ru.pizza.restaurant.dto.new_delivery.Basket;
import ru.pizza.restaurant.dto.new_delivery.IngredientDTO;
import ru.pizza.restaurant.entities.Building;
import ru.pizza.restaurant.entities.Ingredient;
import ru.pizza.restaurant.row_map.new_delivery.GetBasket;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class NewDeliveryBasketDAO extends AbstractDAO implements BaseOperationDB<Map, String> {
    private final NewDeliveryIngredientDAO newDeliveryIngredientDAO;

    protected NewDeliveryBasketDAO(JdbcTemplate jdbcTemplate,
                                   NewDeliveryIngredientDAO newDeliveryIngredientDAO) {
        super(jdbcTemplate);
        this.newDeliveryIngredientDAO = newDeliveryIngredientDAO;
    }

    @Override
    public Map findAll() {
        return null;
    }

    public Map<Basket, List<IngredientDTO>> findAllById(int buildingId) {
        String sql = "select b.id, i.title, weight from new_delivery_basket b join new_delivery_ingredients i on b.id=i.basket_id where b.building_id=?";
        return getJdbcTemplate().query(sql, new GetBasket(), buildingId);

    }

    @Override
    public Map findOneById(String basketId) {
        return null;
    }

    public void delete(String basketId) {
        getJdbcTemplate().update("delete from new_delivery_basket where id=?", basketId);
    }


    public void receive(List<Building> buildings) {
        for (Building building : buildings) {
            String basketId = UUID.randomUUID().toString();
            getJdbcTemplate().update("insert into new_delivery_basket(id, building_id) VALUES (?,?)", basketId, building.getId());
            for (Ingredient ingredient : building.getIngredientList()) {
                getJdbcTemplate().update("insert into new_delivery_ingredients (title, weight, basket_id, is_new, building_id) values (?, ?, ?, ?, ?)",
                        ingredient.getTitle(),
                        ingredient.getWeight(),
                        basketId,
                        ingredient.isNew(),
                        building.getId());
            }
            getJdbcTemplate().update("update building set is_new_delivery = ? where id = ? and is_new_delivery=false",
                    true, building.getId());
        }
    }

    public void accept(String basketId, int buildingId) {
        List<AcceptIngredients> ingredientList = newDeliveryIngredientDAO.findOneById(basketId);

        for (AcceptIngredients ingredient : ingredientList) {
            if (ingredient.isNew()) {
                getJdbcTemplate().update("insert into warehouse (title, weight, building_id) values (?, ?, ?)",
                        ingredient.getTitle(),
                        ingredient.getWeight(),
                        buildingId);
            } else {
                getJdbcTemplate().update(
                        "update warehouse set weight = weight+? where building_id = ? and title = ?",
                        ingredient.getWeight(), buildingId, ingredient.getTitle());
            }
        }

        this.delete(basketId);

        if (this.isEmpty(buildingId)) {
            getJdbcTemplate().update("update new_delivery set is_new_delivery=false where id=?", buildingId);
        }

    }

    private boolean isEmpty(int buildingId) {
        return getJdbcTemplate().query("SELECT count(*) FROM new_delivery_basket WHERE building_id=?",
                (rs, rowNum) -> rs.getInt("count"),
                buildingId).get(0) == 0;
    }
}