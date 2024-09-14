package ru.pizza.restaurant.dao.new_delivery;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import ru.pizza.restaurant.domain.dto.response.new_delivery.BasketDeliveryDTO;
import ru.pizza.restaurant.domain.dto.response.new_delivery.IngredientDeliveryDTO;
import ru.pizza.restaurant.domain.dto.request.from_main_warehouse.IngredientFromMainWarehouseDTO;
import ru.pizza.restaurant.domain.dto.request.from_main_warehouse.NewDeliveryDTO;
import ru.pizza.restaurant.row_map.new_delivery.GetBasketDeliveryRowMap;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NewDeliveryBasketDAO {
    /**
     * {@link NewDeliveryBasketDAO#newDeliveryIngredientDAO}
     * dao для работы с бд в доставке новых ингредиентов
     */
    private final NewDeliveryIngredientDAO newDeliveryIngredientDAO;
    private final JdbcTemplate jdbcTemplate;
    private final PlatformTransactionManager transactionManager;

    /***
     *
     * @param buildingId ID ресторана
     * @return Возвращает все текущие доставки на ресторан по его ID
     */
    public List<BasketDeliveryDTO> findAll(Integer buildingId) {
        String sql = "SELECT b.id, i.title, i.weight, i.is_new FROM new_delivery_basket b JOIN new_delivery_ingredients i ON b.id=i.basket_id WHERE b.building_id=?";
        List<BasketDeliveryDTO> basketDeliveryDTOS = jdbcTemplate.query(sql, new GetBasketDeliveryRowMap(), buildingId);
        if (basketDeliveryDTOS == null || basketDeliveryDTOS.isEmpty()) {
            throw new RuntimeException("Корзина пуста или не существует");
        }
        return basketDeliveryDTOS;
    }


    /***
     * Обновляет базу данных ресторана. Добавляет новые ингредиенты и обновляет количество старых
     * @param buildingId ID ресторана
     * @param basketId ID корзины
     */
    public void update(int buildingId, String basketId) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        try {
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(@NonNull TransactionStatus status) {
                    List<IngredientDeliveryDTO> ingredientList = newDeliveryIngredientDAO.findAll(basketId);
                    if (ingredientList.isEmpty()) {
                        throw new IllegalStateException("Корзина пуста");
                    }
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
                    deleteById(basketId);
                }
            });
        } catch (RuntimeException e) {
            throw new RuntimeException("Сбой при выполнении", e);
        }
    }

    /***
     * Добавляет в базу данных доставки новую доставку. Создает корзину и заполняет ее.
     * Корзина нужна для того, чтобы групировать несколько доставок на один ресторан
     *
     * @param newDeliveryListDTO корзина доставки
     */
    public void save(List<NewDeliveryDTO> newDeliveryListDTO) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);
        transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        try {
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(@NonNull TransactionStatus status) {
                    if (newDeliveryListDTO.isEmpty()) throw new IllegalStateException("Корзина пуста");
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
            });
        } catch (RuntimeException e) {
            throw new RuntimeException("Сбой", e);
        }
    }

    /***
     * Удаляет корзину из базы данных доставки
     * @param id ID корзины
     */
    public void deleteById(String id) {
        try {
            jdbcTemplate.update("delete from new_delivery_basket where id=?", id);
        } catch (RuntimeException e) {
            throw new RuntimeException("Нет такой доставки", e);
        }
    }
}