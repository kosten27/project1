package com.kostenko.dao.impl;

import com.kostenko.dao.DataSourceDB;
import com.kostenko.domain.Client;
import com.kostenko.domain.Order;
import com.kostenko.domain.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderDBDaoTest {

    private static final String URL = "jdbc:h2:mem:LuxoftShop;DB_CLOSE_DELAY=-1";

    private DataSourceDB dataSource;
    private ClientDBDao clientDao;
    private ProductDBDao productDao;
    private OrderDBDao orderDao;

    @Before
    public void setUp() {
        dataSource = new DataSourceDB(URL);
        clientDao = new ClientDBDao(dataSource);
        productDao = new ProductDBDao(dataSource);
        orderDao = new OrderDBDao(dataSource);
    }

    @Ignore
    private Order getTestOrder() {
        Client client = getTestClient();
        Product product = getTestProduct();
        List<Product> products = new ArrayList<>();
        products.add(product);
        return new Order(client, products);
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

    @Ignore
    private Product getTestProduct() {
        long id = 1;
        String name = "test";
        BigDecimal price = new BigDecimal(100);
        return new Product(id, name, price);
    }

    @Test
    public void saveOrderTest() {
        //GIVEN
        Order order = getTestOrder();

        //WHEN
        clientDao.saveClient(order.getClient());
        for (Product product:order.getProducts()) {
            productDao.saveProduct(product);
        }
        boolean result = orderDao.saveOrder(order);
        orderDao.deleteOrder(order.getId());
        clientDao.deleteClient(order.getClient().getId());
        for (Product product:order.getProducts()) {
            productDao.deleteProduct(product.getId());
        }

        //THEN
        Assert.assertTrue(result);
    }

    @Test
    public void orderFoundTest() {
        //GIVEN
        Order order = getTestOrder();

        //WHEN
        clientDao.saveClient(order.getClient());
        for (Product product:order.getProducts()) {
            productDao.saveProduct(product);
        }
        orderDao.saveOrder(order);
        boolean orderFound = orderDao.orderFound(order.getId());
        orderDao.deleteOrder(order.getId());
        clientDao.deleteClient(order.getClient().getId());
        for (Product product:order.getProducts()) {
            productDao.deleteProduct(product.getId());
        }

        //THEN
        Assert.assertTrue(orderFound);
    }

    @Test
    public void deleteOrderTest() {
        //GIVEN
        Order order = getTestOrder();

        //WHEN
        clientDao.saveClient(order.getClient());
        for (Product product:order.getProducts()) {
            productDao.saveProduct(product);
        }
        orderDao.saveOrder(order);
        boolean result = orderDao.deleteOrder(order.getId());
        orderDao.deleteOrder(order.getId());
        clientDao.deleteClient(order.getClient().getId());
        for (Product product:order.getProducts()) {
            productDao.deleteProduct(product.getId());
        }

        //THEN
        Assert.assertTrue(result);
    }

    @Test
    public void updateOrderTest() {
        //GIVEN
        Order order = getTestOrder();
        long productId = 2;
        String productName = "Product2";
        BigDecimal productPrice = new BigDecimal(150);
        Product newProduct = new Product(productId, productName, productPrice);
        List<Product> products = new ArrayList<>();
        products.add(newProduct);

        //WHEN
        clientDao.saveClient(order.getClient());
        for (Product product:order.getProducts()) {
            productDao.saveProduct(product);
        }
        orderDao.saveOrder(order);
        order.setProducts(products);
        productDao.saveProduct(newProduct);
        boolean result = orderDao.updateOrder(order);
        orderDao.deleteOrder(order.getId());
        clientDao.deleteClient(order.getClient().getId());
        for (Product product:order.getProducts()) {
            productDao.deleteProduct(product.getId());
        }

        //THEN
        Assert.assertTrue(result);
    }

    @Test
    public void getOrderTest() {
        //GIVEN
        Order order = getTestOrder();

        //WHEN
        clientDao.saveClient(order.getClient());
        for (Product product:order.getProducts()) {
            productDao.saveProduct(product);
        }
        orderDao.saveOrder(order);
        Order newOrder = orderDao.getOrder(order.getId());
        orderDao.deleteOrder(order.getId());
        clientDao.deleteClient(order.getClient().getId());
        for (Product product:order.getProducts()) {
            productDao.deleteProduct(product.getId());
        }

        //THEN
        Assert.assertNotNull(newOrder);
    }

    @Test
    public void getOrdersTest() {
        //GIVEN
        Order order = getTestOrder();

        //WHEN
        clientDao.saveClient(order.getClient());
        for (Product product:order.getProducts()) {
            productDao.saveProduct(product);
        }
        orderDao.saveOrder(order);
        List<Order> orders = orderDao.getOrders();
        orderDao.deleteOrder(order.getId());
        clientDao.deleteClient(order.getClient().getId());
        for (Product product:order.getProducts()) {
            productDao.deleteProduct(product.getId());
        }

        //THEN
        Assert.assertTrue(orders.size() == 1);
    }

    @Test
    public void getClientOrdersTest() {
        //GIVEN
        Order order = getTestOrder();

        //WHEN
        clientDao.saveClient(order.getClient());
        for (Product product:order.getProducts()) {
            productDao.saveProduct(product);
        }
        orderDao.saveOrder(order);
        List<Order> orders = orderDao.getOrders(order.getClient().getId());
        orderDao.deleteOrder(order.getId());
        clientDao.deleteClient(order.getClient().getId());
        for (Product product:order.getProducts()) {
            productDao.deleteProduct(product.getId());
        }

        //THEN
        Assert.assertTrue(orders.size() == 1);
    }
}