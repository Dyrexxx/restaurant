package ru.pizza.restaurant.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.pizza.restaurant.dao.BuildingDAO;
import ru.pizza.restaurant.dao.new_delivery.NewDeliveryBasketDAO;
import ru.pizza.restaurant.domain.entities.Building;
import ru.pizza.restaurant.domain.entities.Ingredient;
import ru.pizza.restaurant.services.rest.RestBuildingService;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BuildingServiceTest {

    @Mock
    private BuildingDAO mockBuildingDAO;
    @Mock
    private NewDeliveryBasketDAO mockNewDeliveryBasketDAO;

    private RestBuildingService buildingServiceUnderTest;

    @BeforeEach
    void setUp() {
        buildingServiceUnderTest = new RestBuildingService(mockBuildingDAO, mockNewDeliveryBasketDAO);
    }

    @Test
    void testFindAll() {
        // Setup
        final Building building = new Building();
        building.setId(0);
        building.setTitle("title");
        building.setNewDelivery(false);
        final Ingredient ingredient = new Ingredient();
        ingredient.setId(0);
        building.setIngredientList(List.of(ingredient));
        final List<Building> expectedResult = List.of(building);

        // Configure BuildingDAO.findAll(...).
        final Building building1 = new Building();
        building1.setId(0);
        building1.setTitle("title");
        building1.setNewDelivery(false);
        final Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(0);
        building1.setIngredientList(List.of(ingredient1));
        final List<Building> buildings = List.of(building1);
        when(mockBuildingDAO.findAll()).thenReturn(buildings);

        // Run the test
        final List<Building> result = buildingServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_BuildingDAOReturnsNoItems() {
        // Setup
        when(mockBuildingDAO.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Building> result = buildingServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testFindById() {
        // Setup
        final Building expectedResult = new Building();
        expectedResult.setId(0);
        expectedResult.setTitle("title");
        expectedResult.setNewDelivery(false);
        final Ingredient ingredient = new Ingredient();
        ingredient.setId(0);
        expectedResult.setIngredientList(List.of(ingredient));

        // Configure BuildingDAO.findById(...).
        final Building building = new Building();
        building.setId(0);
        building.setTitle("title");
        building.setNewDelivery(false);
        final Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(0);
        building.setIngredientList(List.of(ingredient1));
        when(mockBuildingDAO.findById(0)).thenReturn(building);

        // Run the test
        final Building result = buildingServiceUnderTest.findById(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSave2() {
        // Setup
        final Building building = new Building();
        building.setId(0);
        building.setTitle("title");
        building.setNewDelivery(false);
        final Ingredient ingredient = new Ingredient();
        ingredient.setId(0);
        building.setIngredientList(List.of(ingredient));
        final List<Building> buildings = List.of(building);

        // Run the test
        buildingServiceUnderTest.save(buildings);

        // Verify the results
        // Confirm NewDeliveryBasketDAO.save(...).
        final Building building1 = new Building();
        building1.setId(0);
        building1.setTitle("title");
        building1.setNewDelivery(false);
        final Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(0);
        building1.setIngredientList(List.of(ingredient1));
        final List<Building> buildings1 = List.of(building1);
        verify(mockNewDeliveryBasketDAO).save(buildings1);
    }
}
