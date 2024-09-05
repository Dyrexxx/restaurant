package ru.pizza.restaurant.controllers.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pizza.restaurant.domain.dto.new_delivery.empty_id_model.BasketNewDeliveryIdDTO;
import ru.pizza.restaurant.services.mvc.BuildingService;
import ru.pizza.restaurant.services.mvc.NewDeliveryService;

@Controller
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class NewDeliveryController {
    private final NewDeliveryService newDeliveryService;
    private final BuildingService buildingService;

    @GetMapping("/new-delivery/{buildingId}")
    public String index(@PathVariable int buildingId, Model model) {
        model.addAttribute("newDeliveryList", newDeliveryService.index(buildingId));
        model.addAttribute("building", buildingService.findById(buildingId));
        return "new_delivery";
    }

    @ModelAttribute("emptyDeliveryBasketId")
    public BasketNewDeliveryIdDTO emptyNewDeliveryBasket() {
        return new BasketNewDeliveryIdDTO();
    }
}
