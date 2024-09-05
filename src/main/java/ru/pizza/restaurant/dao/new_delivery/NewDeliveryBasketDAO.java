package ru.pizza.restaurant.dao.new_delivery;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.pizza.restaurant.dao.BasketMethodsDB;
import ru.pizza.restaurant.domain.dto.new_delivery.BasketDeliveryDTO;
import ru.pizza.restaurant.domain.dto.new_delivery.IngredientDeliveryDTO;
import ru.pizza.restaurant.domain.dto.request.from_main_warehouse.IngredientFromMainWarehouseDTO;
import ru.pizza.restaurant.domain.dto.request.from_main_warehouse.NewDeliveryDTO;
import ru.pizza.restaurant.domain.entities.Building;
import ru.pizza.restaurant.domain.entities.Ingredient;
import ru.pizza.restaurant.row_map.new_delivery.GetBasketDeliveryRowMap;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NewDeliveryBasketDAO implements BasketMethodsDB<BasketDeliveryDTO, String, Integer> {
    private final NewDeliveryIngredientDAO newDeliveryIngredientDAO;
    private final JdbcTemplate jdbcTemplate;

    //Пригодится. В main-warehouse организовать отслеживание доставки нового привоза
    @Override
    public List<BasketDeliveryDTO> findAll() {
        return null;
    }

    /***
     *
     * @param buildingId ID ресторана
     * @return Возвращает все текущие доставки на ресторан
     */
    @Override
    public List<BasketDeliveryDTO> findAll(Integer buildingId) {
        String sql = "SELECT b.id, i.title, i.weight, i.is_new FROM new_delivery_basket b JOIN new_delivery_ingredients i ON b.id=i.basket_id WHERE b.building_id=?";
        return jdbcTemplate.query(sql, new GetBasketDeliveryRowMap(), buildingId);
    }

    //Возможно пригодится
    @Override
    public BasketDeliveryDTO findById(String id) {
        return null;
    }


    @Override
    public void update(int buildingId, String basketId) {
        List<IngredientDeliveryDTO> ingredientList = newDeliveryIngredientDAO.findAll(basketId);

        for (IngredientDeliveryDTO ingredient : ingredientList) {
            if (ingredient.isNew()) {
                jdbcTemplate.update("insert into warehouse (title, weight, building_id) values (?, ?, ?)",
                        ingredient.getTitle(),
                        ingredient.getWeight(),
                        buildingId);
            } else {
                jdbcTemplate.update(
                        "update warehouse set weight = weight+? where building_id = ? and title = ?",
                        ingredient.getWeight(), buildingId, ingredient.getTitle());
            }
        }

        this.deleteById(basketId);
    }

    @Override
    public void save(BasketDeliveryDTO basketDeliveryDTO) {

    }
    public void save(NewDeliveryDTO newDeliveryDTO) {
        for (NewDeliveryDTO.ItemDelivery itemDelivery : newDeliveryDTO.getDelivery()) {
            String basketId = UUID.randomUUID().toString();
            jdbcTemplate.update("insert into new_delivery_basket(id, building_id) VALUES (?,?)", basketId, itemDelivery.getBuilding().getId());
            for (IngredientFromMainWarehouseDTO ingredient : itemDelivery.getIngredientList()) {
                jdbcTemplate.update("insert into new_delivery_ingredients (title, weight, is_new, basket_id) values (?, ?, ?, ?)",
                        ingredient.getTitle(),
                        ingredient.getWeight(),
                        ingredient.isNew(),
                        basketId);
            }
        }
    }

    @Override
    public void deleteById(String id) {
        jdbcTemplate.update("delete from new_delivery_basket where id=?", id);
    }
}