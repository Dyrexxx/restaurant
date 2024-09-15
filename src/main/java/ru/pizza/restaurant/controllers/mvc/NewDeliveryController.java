package ru.pizza.restaurant.controllers.mvc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pizza.restaurant.domain.dto.response.new_delivery.thymeleaf.BasketNewDeliveryIdDTO;
import ru.pizza.restaurant.exceptions.NoContentException;
import ru.pizza.restaurant.services.mvc.BuildingService;
import ru.pizza.restaurant.services.mvc.NewDeliveryService;


@Controller
@RequestMapping("/restaurant")
@RequiredArgsConstructor
public class NewDeliveryController {
    /**
     * {@link NewDeliveryController#newDeliveryService сервис для работы с доставкой из main-warehouse}
     */
    private final NewDeliveryService newDeliveryService;
    /**
     * {@link NewDeliveryController#buildingService сервис для работы с ресторанами}
     */
    private final BuildingService buildingService;

    /**
     * @param buildingId ID ресторана
     * @param model      храняться объекты используемые в thymeleaf
     * @return html страницу новых поставок
     */
    @GetMapping("/new-delivery/{buildingId}")
    public String index(@PathVariable int buildingId, Model model) {
        model.addAttribute("newDeliveryList", newDeliveryService.index(buildingId));
        model.addAttribute("building", buildingService.findById(buildingId));
        return "new_delivery";

    }

    /**
     * @return объект для ID ресторана. Хранит ID
     */
    @ModelAttribute("emptyDeliveryBasketId")
    public BasketNewDeliveryIdDTO emptyNewDeliveryBasket() {
        return new BasketNewDeliveryIdDTO();
    }
}
