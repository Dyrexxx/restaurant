package ru.pizza.restaurant.controllers.parent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.pizza.restaurant.dto.new_delivery.Basket;
import ru.pizza.restaurant.dto.new_delivery.IngredientDTO;
import ru.pizza.restaurant.entities.Building;
import ru.pizza.restaurant.entities.Ingredient;
import ru.pizza.restaurant.services.BuildingService;
import ru.pizza.restaurant.services.NewDeliveryService;
import ru.pizza.restaurant.services.OrderBasketService;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class DodoParent extends AbstractController {

    protected Restaurant RESTAURANT_NAME;
    protected final NewDeliveryService newDeliveryService;
    protected final OrderBasketService orderBasketService;


    @Autowired
    public DodoParent(BuildingService buildingService, NewDeliveryService newDeliveryService, OrderBasketService orderBasketService) {
        super(buildingService);
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
        model.addAttribute("orders", orderBasketService.getOrder(RESTAURANT_NAME.id));
        return "orders";
    }

    @PostMapping("/make-order/{orderId}")
    public String makeOrder(@PathVariable String orderId) {
        orderBasketService.done(RESTAURANT_NAME.id, orderId);
        return "redirect:/orders";
    }


    @PostMapping("/accept-delivery")
    public String acceptDelivery(@ModelAttribute Basket emptyBasket) {
        newDeliveryService.accept(emptyBasket.getId(), RESTAURANT_NAME.id);
        return "redirect:/dodo" + RESTAURANT_NAME.id + "/view-new-delivery";
    }

    @ModelAttribute("emptyDeliveryBasket")
    public Basket emptyNewDeliveryBasket() {
        return new Basket();
    }

    @ModelAttribute("building")
    public Building building() {
        return buildingService.get(RESTAURANT_NAME.getId());
    }

    @ModelAttribute("newDelivery")
    public Map<Basket, List<IngredientDTO>> ordersByBuilding() {
        return newDeliveryService.getNewDelivery(RESTAURANT_NAME.getId());
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