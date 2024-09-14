package ru.pizza.restaurant.dao.new_delivery;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.pizza.restaurant.domain.dto.new_delivery.BasketDeliveryDTO;
import ru.pizza.restaurant.domain.dto.new_delivery.IngredientDeliveryDTO;
import ru.pizza.restaurant.domain.dto.request.from_main_warehouse.IngredientFromMainWarehouseDTO;
import ru.pizza.restaurant.domain.dto.request.from_main_warehouse.NewDeliveryDTO;
import ru.pizza.restaurant.row_map.new_delivery.GetBasketDeliveryRowMap;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NewDeliveryBasketDAO {
    private final NewDeliveryIngredientDAO newDeliveryIngredientDAO;
    private final JdbcTemplate jdbcTemplate;

    /***
     *
     * @param buildingId ID ресторана
     * @return Возвращает все текущие доставки на ресторан по его ID
     */
    public List<BasketDeliveryDTO> findAll(Integer buildingId) {
        String sql = "SELECT b.id, i.title, i.weight, i.is_new FROM new_delivery_basket b JOIN new_delivery_ingredients i ON b.id=i.basket_id WHERE b.building_id=?";
        return jdbcTemplate.query(sql, new GetBasketDeliveryRowMap(), buildingId);
    }


    /***
     * Обновляет базу данных ресторана. Добавляет новые ингредиенты и обновляет количество старых
     * @param buildingId ID ресторана
     * @param basketId ID корзины
     */
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

    /***
     * Добавляет в базу данных доставки новую доставку. Создает корзину и заполняет ее.
     * Корзина нужна для того, чтобы групировать несколько доставок на один ресторан
     *
     * @param newDeliveryListDTO корзина доставки
     */
    public void save(List<NewDeliveryDTO> newDeliveryListDTO) {
        for (NewDeliveryDTO itemDelivery : newDeliveryListDTO) {
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

    /***
     * Удаляет корзину из базы данных доставки
     * @param id ID корзины
     */
    public void deleteById(String id) {
        jdbcTemplate.update("delete from new_delivery_basket where id=?", id);
    }
}