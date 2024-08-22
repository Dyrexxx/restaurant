package ru.pizza.restaurant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.pizza.restaurant.controllers.parent.AbstractController;
import ru.pizza.restaurant.dto.order_basket.OrderBasketContentDTO;
import ru.pizza.restaurant.dto.order_basket.OrderProductContentDTO;
import ru.pizza.restaurant.models.Basket;
import ru.pizza.restaurant.entities.Building;
import ru.pizza.restaurant.entities.Ingredient;
import ru.pizza.restaurant.services.BuildingService;
import ru.pizza.restaurant.services.IngredientService;
import ru.pizza.restaurant.services.OrderBasketService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dodo")
public class MainDodoController extends AbstractController {

    private final IngredientService ingredientService;
    private final OrderBasketService orderBasketService;

    @Autowired
    public MainDodoController(BuildingService buildingService, IngredientService ingredientService, OrderBasketService orderBasketService) {
        super(buildingService);
        this.ingredientService = ingredientService;
        this.orderBasketService = orderBasketService;
    }

    @GetMapping("/buildings")
    public ResponseEntity<List<Building>> indexBuilding() {
        return new ResponseEntity<>(buildingService.index(), HttpStatus.OK);
    }

    @GetMapping("/ingredients")
    public ResponseEntity<List<Ingredient>> indexIngredient() {
        return new ResponseEntity<>(ingredientService.index(), HttpStatus.OK);
    }

    @PostMapping("/new-delivery")
    @ResponseBody
    public void delivery(@RequestBody List<Building> buildings) {
        buildingService.receiveNewDelivery(buildings);
    }

    @PostMapping("/newOrder")
    @ResponseBody
    public void newOrder(@RequestBody Basket basket) {
        orderBasketService.save(basket);
    }

    @GetMapping("/get-orders")
    public ResponseEntity<Map<OrderBasketContentDTO, List<OrderProductContentDTO>>> getOrders() {
        return new ResponseEntity<>(orderBasketService.getOrders(), HttpStatus.OK);
    }
}