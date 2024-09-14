package ru.pizza.restaurant.services.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pizza.restaurant.dao.order_basket.OrderBasketDAO;
import ru.pizza.restaurant.domain.dto.response.order_basket.BasketOrderDTO;


import java.util.List;


@Service
@RequiredArgsConstructor
public class RestOrderBasketService {
    private final OrderBasketDAO orderBasketDAO;

    @Transactional(readOnly = true)
    public List<BasketOrderDTO> index() {
        return orderBasketDAO.findAll();
    }

    @Transactional(readOnly = true)
    public List<BasketOrderDTO> index(Integer id) {
        return orderBasketDAO.findAll(id);
    }

    /**
     * Обновляет корзину онлайн заказов определенного ресторана
     * @param buildingId ID ресторана
     * @param id ID корзины онлайн заказа
     */
    @Transactional
    public void update(int buildingId, String id) throws UnsupportedOperationException{
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