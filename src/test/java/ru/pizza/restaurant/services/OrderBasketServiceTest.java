package ru.pizza.restaurant.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.pizza.restaurant.dao.order_basket.OrderBasketDAO;
import ru.pizza.restaurant.domain.dto.order_basket.BasketOrderDTO;
import ru.pizza.restaurant.domain.dto.order_basket.ProductOrderDTO;
import ru.pizza.restaurant.services.rest.RestOrderBasketService;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderBasketServiceTest {

    @Mock
    private OrderBasketDAO mockOrderBasketDAO;

    private RestOrderBasketService orderBasketServiceUnderTest;

    @BeforeEach
    void setUp() {
        orderBasketServiceUnderTest = new RestOrderBasketService(mockOrderBasketDAO);
    }

    @Test
    void testFindAll1() {
        // Setup
        final BasketOrderDTO basketOrderDTO = new BasketOrderDTO();
        basketOrderDTO.setId("id");
        basketOrderDTO.setFio("fio");
        basketOrderDTO.setAddress("address");
        final ProductOrderDTO productOrderDTO = new ProductOrderDTO();
        productOrderDTO.setTitle("title");
        basketOrderDTO.setProductsList(List.of(productOrderDTO));
        final List<BasketOrderDTO> expectedResult = List.of(basketOrderDTO);

        // Configure OrderBasketDAO.findAll(...).
        final BasketOrderDTO basketOrderDTO1 = new BasketOrderDTO();
        basketOrderDTO1.setId("id");
        basketOrderDTO1.setFio("fio");
        basketOrderDTO1.setAddress("address");
        final ProductOrderDTO productOrderDTO1 = new ProductOrderDTO();
        productOrderDTO1.setTitle("title");
        basketOrderDTO1.setProductsList(List.of(productOrderDTO1));
        final List<BasketOrderDTO> basketOrderDTOS = List.of(basketOrderDTO1);
        when(mockOrderBasketDAO.findAll()).thenReturn(basketOrderDTOS);

        // Run the test
        final List<BasketOrderDTO> result = orderBasketServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll1_OrderBasketDAOReturnsNoItems() {
        // Setup
        when(mockOrderBasketDAO.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<BasketOrderDTO> result = orderBasketServiceUnderTest.findAll();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testFindAll2() {
        // Setup
        final BasketOrderDTO basketOrderDTO = new BasketOrderDTO();
        basketOrderDTO.setId("id");
        basketOrderDTO.setFio("fio");
        basketOrderDTO.setAddress("address");
        final ProductOrderDTO productOrderDTO = new ProductOrderDTO();
        productOrderDTO.setTitle("title");
        basketOrderDTO.setProductsList(List.of(productOrderDTO));
        final List<BasketOrderDTO> expectedResult = List.of(basketOrderDTO);

        // Configure OrderBasketDAO.findAll(...).
        final BasketOrderDTO basketOrderDTO1 = new BasketOrderDTO();
        basketOrderDTO1.setId("id");
        basketOrderDTO1.setFio("fio");
        basketOrderDTO1.setAddress("address");
        final ProductOrderDTO productOrderDTO1 = new ProductOrderDTO();
        productOrderDTO1.setTitle("title");
        basketOrderDTO1.setProductsList(List.of(productOrderDTO1));
        final List<BasketOrderDTO> basketOrderDTOS = List.of(basketOrderDTO1);
        when(mockOrderBasketDAO.findAll(0)).thenReturn(basketOrderDTOS);

        // Run the test
        final List<BasketOrderDTO> result = orderBasketServiceUnderTest.findAll(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testFindAll2_OrderBasketDAOReturnsNoItems() {
        // Setup
        when(mockOrderBasketDAO.findAll(0)).thenReturn(Collections.emptyList());

        // Run the test
        final List<BasketOrderDTO> result = orderBasketServiceUnderTest.findAll(0);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testUpdate() {
        // Setup
        // Run the test
        orderBasketServiceUnderTest.update(0, "id");

        // Verify the results
        verify(mockOrderBasketDAO).update(0, "id");
    }

    @Test
    void testSave() {
        // Setup
        final BasketOrderDTO basketOrderDTO = new BasketOrderDTO();
        basketOrderDTO.setId("id");
        basketOrderDTO.setFio("fio");
        basketOrderDTO.setAddress("address");
        final ProductOrderDTO productOrderDTO = new ProductOrderDTO();
        productOrderDTO.setTitle("title");
        basketOrderDTO.setProductsList(List.of(productOrderDTO));

        // Run the test
        orderBasketServiceUnderTest.save(basketOrderDTO);

        // Verify the results
        // Confirm OrderBasketDAO.save(...).
        final BasketOrderDTO basketOrderDTO1 = new BasketOrderDTO();
        basketOrderDTO1.setId("id");
        basketOrderDTO1.setFio("fio");
        basketOrderDTO1.setAddress("address");
        final ProductOrderDTO productOrderDTO1 = new ProductOrderDTO();
        productOrderDTO1.setTitle("title");
        basketOrderDTO1.setProductsList(List.of(productOrderDTO1));
        verify(mockOrderBasketDAO).save(basketOrderDTO1);
    }
}
