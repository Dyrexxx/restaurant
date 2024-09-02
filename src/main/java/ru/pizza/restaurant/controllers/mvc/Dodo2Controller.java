package ru.pizza.restaurant.controllers.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.pizza.restaurant.controllers.mvc.parent.DodoParent;
import ru.pizza.restaurant.services.rest.RestBuildingService;
import ru.pizza.restaurant.services.rest.RestNewDeliveryService;
import ru.pizza.restaurant.services.rest.RestOrderBasketService;

@Controller
@RequestMapping("/dodo2")
public final class Dodo2Controller extends DodoParent {

    {
        RESTAURANT_NAME = Restaurant.DODO2;
    }


    public Dodo2Controller(RestBuildingService buildingService, RestNewDeliveryService newDeliveryService, RestOrderBasketService orderBasketService) {
        super(buildingService, newDeliveryService, orderBasketService);
    }
}