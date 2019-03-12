package com.kostenko.services.impl;

import com.kostenko.dao.ClientDao;
import com.kostenko.dao.OrderDao;
import com.kostenko.dao.ProductDao;
import com.kostenko.domain.Client;
import com.kostenko.domain.Order;
import com.kostenko.domain.Product;
import com.kostenko.services.OrderService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Mock
    private OrderDao orderDao;
    @Mock
    private ClientDao clientDao;
    @Mock
    private ProductDao productDao;

    private OrderService orderService;
    private Product product;
    private Client client;

    @Before
    public void setUp() throws Exception {
        orderService = new OrderServiceImpl(orderDao, clientDao, productDao);
        product = getTestProduct();
        client = getTestClient();
    }

    @Ignore
    private Product getTestProduct() {
        long productId = 1;
        String productName = "test";
        BigDecimal productPrice = new BigDecimal(10);
        return new Product(productId, productName, productPrice);
    }

    @Ignore
    private Client getTestClient() {
        long clientId = 1;
        String clientName = "test";
        String clientSurname = "test";
        int clientAge = 10;
        String clientPhone = "0502254850";
        String clientEmail = "test@gmail.com";
        return new Client(clientId, clientName, clientSurname, clientAge, clientEmail, clientPhone);
    }

    @Test
    public void createOrderTest() {
        //GIVEN
        when(clientDao.getClient(client.getId())).thenReturn(client);
        List<Long> products = new ArrayList<>();
        products.add(product.getId());
        when(productDao.getProduct(product.getId())).thenReturn(product);
        when(orderDao.saveOrder(any())).thenReturn(true);

        //WHEN
        orderService.createOrder(client.getId(), products);

        //THEN
        verify(clientDao, times(1)).getClient(client.getId());
        verify(productDao, times(1)).getProduct(product.getId());
        verify(orderDao, times(1)).saveOrder(any());
    }

    @Test
    public void orderFoundTest() {
        //GIVEN
        long id = 1;
        when(orderDao.orderFound(id)).thenReturn(true);

        //WHEN
        orderService.orderFound(id);

        //THEN
        verify(orderDao, times(1)).orderFound(id);
    }

    @Test
    public void modifyOrderTest() {
        //GIVEN
        long orderId = 1;
        List<Product> products = new ArrayList<>();
        products.add(product);
        Order order = new Order(orderId, client, products);
        List<Long> productsId = new ArrayList<>();
        productsId.add(product.getId());
        when(productDao.getProduct(product.getId())).thenReturn(product);
        when(orderDao.getOrder(orderId)).thenReturn(order);
        when(orderDao.updateOrder(any())).thenReturn(true);

        //WHEN
        orderService.modifyOrder(orderId, productsId);

        //THEN
        verify(productDao, times(1)).getProduct(product.getId());
        verify(orderDao, times(1)).updateOrder(any());
    }

    @Test
    public void deleteOrderTest() {
        //GIVEN
        long id = 1;
        when(orderDao.deleteOrder(id)).thenReturn(true);

        //WHEN
        orderService.deleteOrder(id);

        //THEN
        verify(orderDao, times(1)).deleteOrder(id);
    }

    @Test
    public void showAllOrdersTest() {
        //GIVEN
        List<Order> orders = new ArrayList<>();
        when(orderDao.getOrders()).thenReturn(orders);

        //WHEN
        orderService.showOrders();

        //THEN
        verify(orderDao, times(1)).getOrders();
    }

    @Test
    public void showClientOrders() {
        //GIVEN
        List<Order> orders = new ArrayList<>();
        when(orderDao.getOrders(client.getId())).thenReturn(orders);

        //WHEN
        orderService.showOrders();

        //THEN
        verify(orderDao, times(1)).getOrders();
    }
}