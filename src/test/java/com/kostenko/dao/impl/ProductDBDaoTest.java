package com.kostenko.dao.impl;

import com.kostenko.dao.DataSourceDB;
import com.kostenko.domain.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.List;

public class ProductDBDaoTest {

    private static final String URL = "jdbc:h2:mem:LuxoftShop;DB_CLOSE_DELAY=-1";

    private DataSourceDB dataSource;
    private ProductDBDao productDao;

    @Before
    public void setUp() {
        dataSource = new DataSourceDB(URL);
        productDao = new ProductDBDao(dataSource);
    }

    @Test
    public void saveProductTest() {
        //GIVEN
        Product product = getTestProduct();

        //WHEN
        boolean result = productDao.saveProduct(product);
        productDao.deleteProduct(product.getId());

        //THEN
        Assert.assertTrue(result);
    }

    @Ignore
    private Product getTestProduct() {
        long id = 1;
        String name = "test";
        BigDecimal price = new BigDecimal(100);
        return new Product(id, name, price);
    }

    @Test
    public void productFoundTest() {
        //GIVEN
        Product product = getTestProduct();

        //WHEN
        productDao.saveProduct(product);
        boolean productFound = productDao.productFound(product.getId());
        productDao.deleteProduct(product.getId());

        //THEN
        Assert.assertTrue(productFound);
    }

    @Test
    public void updateProductTest() {
        //GIVEN
        Product product = getTestProduct();
        String newName = "new name";

        //WHEN
        productDao.saveProduct(product);
        product.setName(newName);
        boolean result = productDao.updateProduct(product);
        productDao.deleteProduct(product.getId());

        //THEN
        Assert.assertTrue(result);
    }

    @Test
    public void deleteProductTest() {
        //GIVEN
        Product product = getTestProduct();

        //WHEN
        productDao.saveProduct(product);
        boolean result = productDao.deleteProduct(product.getId());

        //THEN
        Assert.assertTrue(result);
    }

    @Test
    public void getProductTest() {
        //GIVEN
        Product product = getTestProduct();

        //WHEN
        productDao.saveProduct(product);
        Product newProduct = productDao.getProduct(product.getId());
        productDao.deleteProduct(product.getId());

        //THEN
        Assert.assertNotNull(newProduct);
    }

    @Test
    public void getProducts() {
        //GIVEN
        Product product = getTestProduct();

        //WHEN
        productDao.saveProduct(product);
        List<Product> products = productDao.getProducts();
        productDao.deleteProduct(product.getId());

        //THEN
        Assert.assertTrue(products.size() == 1);
    }
}