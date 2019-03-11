package com.kostenko.services.impl;

import com.kostenko.dao.ProductDao;
import com.kostenko.domain.Product;
import com.kostenko.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    private ProductDao productDao;

    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        productService = new ProductServiceImpl(productDao);
    }

    @Test
    public void createProductWithFullParametersTest() {
        //GIVEN
        String name = "test";
        BigDecimal price = new BigDecimal(10);
        when(productDao.saveProduct(any())).thenReturn(true);

        //WHEN
        productService.createProduct(name, price);

        //THEN
        verify(productDao, times(1)).saveProduct(any());
    }

    @Test
    public void productFoundTest() {
        //GIVEN
        long id = 1;
        when(productDao.productFound(id)).thenReturn(true);

        //WHEN
        boolean productFound = productService.productFound(id);

        //THEN
        assertTrue(productFound);
        verify(productDao, times(1)).productFound(id);
    }

    @Test
    public void modifyProductTest() {
        //GIVEN
        long id = 1;
        String name = "test";
        String newName = "new product";
        BigDecimal price = new BigDecimal(10);
        Product product = new Product(id, name, price);
        when(productDao.getProduct(id)).thenReturn(product);
        when(productDao.updateProduct(any())).thenReturn(true);

        //WHEN
        productService.modifyProduct(id, newName, price);

        //THEN
        verify(productDao, times(1)).updateProduct(any());
    }

    @Test
    public void deleteProductTest() {
        //GIVEN
        long id = 1;
        when(productDao.deleteProduct(id)).thenReturn(true);

        //WHEN
        productService.deleteProduct(id);

        //THEN
        verify(productDao, times(1)).deleteProduct(id);
    }

    @Test
    public void showProductsTest() {
        //GIVEN
        List<Product> products = new ArrayList<>();
        when(productDao.getProducts()).thenReturn(products);

        //WHEN
        productService.showProducts();

        //THEN
        verify(productDao, times(1)).getProducts();
    }
}