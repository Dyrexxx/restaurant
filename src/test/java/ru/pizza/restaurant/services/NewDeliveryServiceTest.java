package ru.pizza.restaurant.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.pizza.restaurant.dao.new_delivery.NewDeliveryBasketDAO;
import ru.pizza.restaurant.dto.new_delivery.base.BasketDeliveryDTO;
import ru.pizza.restaurant.dto.new_delivery.base.IngredientDeliveryDTO;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NewDeliveryServiceTest {

    @Mock
    private NewDeliveryBasketDAO mockNewDeliveryBasketDAO;

    private NewDeliveryService newDeliveryServiceUnderTest;

    @BeforeEach
    void setUp() {
        newDeliveryServiceUnderTest = new NewDeliveryService(mockNewDeliveryBasketDAO);
    }

    @Test
    void testFindAll1() {
        // Setup
        final BasketDeliveryDTO basketDeliveryDTO = new BasketDeliveryDTO();
        basketDeliveryDTO.setId("id");
        final IngredientDeliveryDTO ingredientDeliveryDTO = new IngredientDeliveryDTO();
        ingredientDeliveryDTO.setTitle("title");
        ingredientDeliveryDTO.setWeight(0);
        basketDeliveryDTO.setIngredientList(List.of(ingredientDeliveryDTO));
        final List<BasketDeliveryDTO> expectedResult = List.of(basketDeliveryDTO);

        // Configure NewDeliveryBasketDAO.findAll(...).
        final BasketDeliveryDTO basketDeliveryDTO1 = new BasketDeliveryDTO();
        basketDeliveryDTO1.setId("id");
        final IngredientDeliveryDTO ingredientDeliveryDTO1 = new IngredientDeliveryDTO();
        ingredientDeliveryDTO1.setTitle("title");
        ingredientDeliveryDTO1.setWeight(0);
        basketDeliveryDTO1.setIngredientList(List.of(ingredientDeliveryDTO1));
        final List<BasketDeliveryDTO> basketDeliveryDTOS = List.of(basketDeliveryDTO1);
        when(mockNewDeliveryBasketDAO.findAll(0)).thenReturn(basketDeliveryDTOS);

        // Run the test
        final List<BasketDeliveryDTO> result = newDeliveryServiceUnderTest.findAll(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll1_NewDeliveryBasketDAOReturnsNoItems() {
        // Setup
        when(mockNewDeliveryBasketDAO.findAll(0)).thenReturn(Collections.emptyList());

        // Run the test
        final List<BasketDeliveryDTO> result = newDeliveryServiceUnderTest.findAll(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testUpdate() {
        // Setup
        // Run the test
        newDeliveryServiceUnderTest.update(0, "id");

        // Verify the results
        verify(mockNewDeliveryBasketDAO).update(0, "id");
    }
}
