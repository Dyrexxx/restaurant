package ru.pizza.restaurant.services.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pizza.restaurant.dao.order_basket.OrderBasketDAO;
import ru.pizza.restaurant.domain.dto.order_basket.BasketOrderDTO;


import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RestOrderBasketService {
    private final OrderBasketDAO orderBasketDAO;

    public List<BasketOrderDTO> index() {
        return orderBasketDAO.findAll();
    }

    public List<BasketOrderDTO> index(Integer id) {
        return orderBasketDAO.findAll(id);
    }

    /**
     * Обновляет корзину онлайн заказов определенного ресторана
     * @param buildingId ID ресторана
     * @param id ID корзины онлайн заказа
     */
    @Transactional
    public void update(int buildingId, String id) {
        orderBasketDAO.update(buildingId, id);
    }

    /**
     * Сохраняет онлайн-заказ
     *
     * @param basketOrderDTO онлайн-заказ
     */
    @Transactional
    public void save(BasketOrderDTO basketOrderDTO) {
        orderBasketDAO.save(basketOrderDTO);
    }

}