package ru.pizza.restaurant.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pizza.restaurant.dao.order_basket.OrderBasketDAO;
import ru.pizza.restaurant.dto.order_basket.BasketOrderDTO;
import ru.pizza.restaurant.global_parent.BasketMethodsDB;


import java.util.List;



@Service
@Transactional(readOnly = true)
public class OrderBasketService implements BasketMethodsDB<BasketOrderDTO, String, Integer> {
    private final OrderBasketDAO orderBasketDAO;

    @Autowired
    public OrderBasketService(OrderBasketDAO orderBasketDAO) {
        this.orderBasketDAO = orderBasketDAO;
    }

    @Override
    public List<BasketOrderDTO> findAll() {
        return orderBasketDAO.findAll();
    }
    @Override
    public List<BasketOrderDTO> findAll(Integer id) {
        return orderBasketDAO.findAll(id);
    }
    @Override
    public BasketOrderDTO findById(String id) {
        return null;
    }
    @Override
    @Transactional
    public void update(int buildingId, String id) {
        orderBasketDAO.update(buildingId, id);
    }

    @Transactional
    public void save(BasketOrderDTO basketOrderDTO) {
        orderBasketDAO.save(basketOrderDTO);
    }

    @Override
    @Transactional
    public void deleteById(String id) {

    }
}