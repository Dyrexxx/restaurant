package ru.pizza.restaurant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.pizza.restaurant.controllers.parent.AbstractController;
import ru.pizza.restaurant.dto.order_basket.BasketOrderDTO;
import ru.pizza.restaurant.entities.Building;
import ru.pizza.restaurant.entities.Ingredient;
import ru.pizza.restaurant.services.BuildingService;
import ru.pizza.restaurant.services.WarehouseService;
import ru.pizza.restaurant.services.OrderBasketService;

import java.util.List;

@Controller
@RequestMapping("/dodo")
public class MainDodoController extends AbstractController {

    private final WarehouseService ingredientService;
    private final OrderBasketService orderBasketService;

    @Autowired
    public MainDodoController(BuildingService buildingService, WarehouseService ingredientService, OrderBasketService orderBasketService) {
        super(buildingService);
        this.ingredientService = ingredientService;
        this.orderBasketService = orderBasketService;
    }

    @GetMapping("/buildings")
    public ResponseEntity<List<Building>> indexBuilding() {
        return new ResponseEntity<>(buildingService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/ingredients")
    public ResponseEntity<List<Ingredient>> indexIngredient() {
        return new ResponseEntity<>(ingredientService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/new-delivery")
    @ResponseBody
    public void delivery(@RequestBody List<Building> buildings) {
        buildingService.save(buildings);
    }

    @PostMapping("/newOrder")
    @ResponseBody
    public void newOrder(@RequestBody BasketOrderDTO basketOrderDTO) {
        orderBasketService.save(basketOrderDTO);
    }

    @GetMapping("/get-orders")
    public ResponseEntity<List<BasketOrderDTO>> getOrders() {
        return new ResponseEntity<>(orderBasketService.findAll(), HttpStatus.OK);
    }
}