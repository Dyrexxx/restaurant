package ru.pizza.restaurant.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.pizza.restaurant.dao.WarehouseDAO;
import ru.pizza.restaurant.domain.entities.Building;
import ru.pizza.restaurant.domain.entities.Ingredient;
import ru.pizza.restaurant.services.rest.RestWarehouseService;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WarehouseServiceTest {

    @Mock
    private WarehouseDAO mockWarehouseDAO;

    private RestWarehouseService warehouseServiceUnderTest;

    @BeforeEach
    void setUp() {
        warehouseServiceUnderTest = new RestWarehouseService(mockWarehouseDAO);
    }

    @Test
    void testFindAll() {
        // Setup
        final Ingredient ingredient = new Ingredient();
        ingredient.setId(0);
        ingredient.setTitle("title");
        ingredient.setWeight(0);
        final Building building = new Building();
        building.setId(0);
        ingredient.setBuilding(building);
        final List<Ingredient> expectedResult = List.of(ingredient);

        // Configure WarehouseDAO.findAll(...).
        final Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(0);
        ingredient1.setTitle("title");
        ingredient1.setWeight(0);
        final Building building1 = new Building();
        building1.setId(0);
        ingredient1.setBuilding(building1);
        final List<Ingredient> ingredients = List.of(ingredient1);
        when(mockWarehouseDAO.findAll()).thenReturn(ingredients);

        // Run the test
        final List<Ingredient> result = warehouseServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll_WarehouseDAOReturnsNoItems() {
        // Setup
        when(mockWarehouseDAO.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Ingredient> result = warehouseServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testFindById() {
        // Setup
        final Ingredient expectedResult = new Ingredient();
        expectedResult.setId(0);
        expectedResult.setTitle("title");
        expectedResult.setWeight(0);
        final Building building = new Building();
        building.setId(0);
        expectedResult.setBuilding(building);

        // Configure WarehouseDAO.findById(...).
        final Ingredient ingredient = new Ingredient();
        ingredient.setId(0);
        ingredient.setTitle("title");
        ingredient.setWeight(0);
        final Building building1 = new Building();
        building1.setId(0);
        ingredient.setBuilding(building1);
        when(mockWarehouseDAO.findById(0)).thenReturn(ingredient);

        // Run the test
        final Ingredient result = warehouseServiceUnderTest.findById(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testSave() {
        // Setup
        final Ingredient ingredient = new Ingredient();
        ingredient.setId(0);
        ingredient.setTitle("title");
        ingredient.setWeight(0);
        final Building building = new Building();
        building.setId(0);
        ingredient.setBuilding(building);

        // Run the test
        warehouseServiceUnderTest.save(ingredient);

        // Verify the results
        // Confirm WarehouseDAO.save(...).
        final Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(0);
        ingredient1.setTitle("title");
        ingredient1.setWeight(0);
        final Building building1 = new Building();
        building1.setId(0);
        ingredient1.setBuilding(building1);
        verify(mockWarehouseDAO).save(ingredient1);
    }

    @Test
    void testDeleteById() {
        // Setup
        // Run the test
        warehouseServiceUnderTest.deleteById(0);

        // Verify the results
        verify(mockWarehouseDAO).deleteById(0);
    }
}