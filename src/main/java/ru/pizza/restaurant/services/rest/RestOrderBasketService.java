package ru.pizza.restaurant.services.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pizza.restaurant.dao.order_basket.OrderBasketDAO;
import ru.pizza.restaurant.domain.dto.order_basket.BasketOrderDTO;
import ru.pizza.restaurant.services.BaseMethodsBasketService;
import ru.pizza.restaurant.services.BaseMethodsService;


import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RestOrderBasketService implements BaseMethodsBasketService<BasketOrderDTO, Integer>, BaseMethodsService<BasketOrderDTO, Integer> {
    private final OrderBasketDAO orderBasketDAO;

    @Override
    public List<BasketOrderDTO> index() {
        return orderBasketDAO.findAll();
    }

    @Override
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