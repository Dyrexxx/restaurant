package ru.pizza.restaurant.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.pizza.restaurant.dao.BuildingDAO;
import ru.pizza.restaurant.dao.NewDeliveryBasketDAO;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BuildingServiceTest {
    @Mock
    private BuildingDAO buildingDAO;
    @Mock
    private NewDeliveryBasketDAO newDeliveryBasketDAO;
    @InjectMocks
    private BuildingService buildingService;

    @Test
    void index() {
    }

    @Test
    void get() {
    }

    @Test
    void receiveNewDelivery() {
    }
}