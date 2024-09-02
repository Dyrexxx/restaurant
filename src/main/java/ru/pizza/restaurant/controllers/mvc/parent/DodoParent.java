package ru.pizza.restaurant.controllers.mvc.parent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.pizza.restaurant.domain.dto.new_delivery.base.BasketDeliveryDTO;
import ru.pizza.restaurant.domain.dto.new_delivery.transfer.BasketTransferDeliveryDTO;
import ru.pizza.restaurant.domain.entities.Building;
import ru.pizza.restaurant.services.rest.RestBuildingService;
import ru.pizza.restaurant.services.rest.RestNewDeliveryService;
import ru.pizza.restaurant.services.rest.RestOrderBasketService;

import java.util.List;


public abstract class DodoParent {

    protected Restaurant RESTAURANT_NAME;
    private final RestBuildingService buildingService;
    private final RestNewDeliveryService newDeliveryService;
    private final RestOrderBasketService orderBasketService;


    @Autowired
    public DodoParent(RestBuildingService buildingService, RestNewDeliveryService newDeliveryService, RestOrderBasketService orderBasketService) {
        this.buildingService = buildingService;
        this.newDeliveryService = newDeliveryService;
        this.orderBasketService = orderBasketService;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/view-new-delivery")
    public String viewNewDelivery() {
        return "new_delivery";
    }

    @GetMapping("/view-orders")
    public String viewOrders(Model model) {
        model.addAttribute("orders", orderBasketService.findAll(RESTAURANT_NAME.id));
        return "orders";
    }

    @PostMapping("/make-order/{orderId}")
    public String makeOrder(@PathVariable String orderId) {
        orderBasketService.update(RESTAURANT_NAME.id, orderId);
        return "redirect:/orders";
    }


    @PostMapping("/accept-delivery/{basketId}")
    public String acceptDelivery(@PathVariable String basketId) {
        newDeliveryService.update( RESTAURANT_NAME.id, basketId);
        return "redirect:/dodo" + RESTAURANT_NAME.id + "/view-new-delivery";
    }

    @ModelAttribute("emptyDeliveryBasket")
    public BasketTransferDeliveryDTO emptyNewDeliveryBasket() {
        return new BasketTransferDeliveryDTO();
    }

    @ModelAttribute("building")
    public Building building() {
        return buildingService.findById(RESTAURANT_NAME.getId());
    }

    @ModelAttribute("newDelivery")
    public List<BasketDeliveryDTO> ordersByBuilding() {
        return newDeliveryService.findAll(RESTAURANT_NAME.getId());
    }

    @Getter
    @AllArgsConstructor
    public enum Restaurant {
        DODO1(1),
        DODO2(2),
        DODO3(3),
        DODO4(4),
        DODO5(5);
        private final int id;
    }
}