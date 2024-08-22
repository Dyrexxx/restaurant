package ru.pizza.restaurant.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pizza.restaurant.controllers.parent.DodoParent;
import ru.pizza.restaurant.services.BuildingService;
import ru.pizza.restaurant.services.NewDeliveryService;
import ru.pizza.restaurant.services.OrderBasketService;

@Controller
@RequestMapping("/dodo2")
public final class Dodo2Controller extends DodoParent {

    {
        RESTAURANT_NAME = Restaurant.DODO2;
    }


    public Dodo2Controller(BuildingService buildingService, NewDeliveryService newDeliveryService, OrderBasketService orderBasketService) {
        super(buildingService, newDeliveryService, orderBasketService);
    }
}