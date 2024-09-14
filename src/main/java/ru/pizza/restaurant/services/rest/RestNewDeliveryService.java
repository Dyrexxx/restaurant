package ru.pizza.restaurant.services.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pizza.restaurant.dao.new_delivery.NewDeliveryBasketDAO;
import ru.pizza.restaurant.domain.dto.response.new_delivery.BasketDeliveryDTO;
import ru.pizza.restaurant.domain.dto.request.from_main_warehouse.NewDeliveryDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestNewDeliveryService {
    private final NewDeliveryBasketDAO newDeliveryBasketDAO;

    /**
     *
     * @param id ID ресторана
     * @return Возвращает все доставки, которые в пути, определенного ресторана
     */
    @Transactional(readOnly = true)
    public List<BasketDeliveryDTO> index(Integer id) {
        return newDeliveryBasketDAO.findAll(id);
    }

    /**
     * Обновляет бд доставки. Если принять доставку, данные удаляться из бд доставки и сопоставяться
     * с бд склада определенного ресторана
     *
     * @param buildingId ID ресторана
     * @param id ID корзины
     */
    @Transactional
    public void update(int buildingId, String id) {
        newDeliveryBasketDAO.update(buildingId, id);
    }

    /**
     * Сохраняет новую доставку, которая уже в пути, в бд ожидаемых доставок
     *
     * @param newDeliveryList данные доставки
     */
    @Transactional
    public void save(List<NewDeliveryDTO> newDeliveryList) {
        newDeliveryBasketDAO.save(newDeliveryList);
    }
}