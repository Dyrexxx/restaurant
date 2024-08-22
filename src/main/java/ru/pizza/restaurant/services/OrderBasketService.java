package ru.pizza.restaurant.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pizza.restaurant.dao.OrderBasketDAO;
import ru.pizza.restaurant.dto.order_basket.OrderBasketContentDTO;
import ru.pizza.restaurant.dto.order_basket.OrderProductContentDTO;
import ru.pizza.restaurant.models.Basket;

import java.util.List;
import java.util.Map;


@Service
@Transactional(readOnly = true)
public class OrderBasketService {
    private final OrderBasketDAO orderBasketDAO;

    @Autowired
    public OrderBasketService(OrderBasketDAO orderBasketDAO) {
        this.orderBasketDAO = orderBasketDAO;
    }

    public Map<OrderBasketContentDTO, List<OrderProductContentDTO>> getOrders() {
        return orderBasketDAO.findAll();

    }

    public Map<OrderBasketContentDTO, List<OrderProductContentDTO>> getOrder(int id) {
        return orderBasketDAO.findAllById(id);
    }

    @Transactional
    public void done(int buildingId, String orderId) {
        orderBasketDAO.doneOrder(buildingId, orderId);
    }

    @Transactional
    public void save(Basket basket) {
        orderBasketDAO.save(basket);
    }
}