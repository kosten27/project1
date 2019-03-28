package com.kostenko.dao.impl;

import com.kostenko.dao.ProductDao;
import com.kostenko.domain.Product;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Component
public class ProductEMDao implements ProductDao {

    EntityManager entityManager;

    public ProductEMDao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit");
        entityManager = factory.createEntityManager();
    }

    @Override
    public boolean saveProduct(Product product) {
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public boolean productFound(long productId) {
        Product product = entityManager.find(Product.class, productId);
        return product != null;
    }

    @Override
    public boolean updateProduct(Product product) {
        entityManager.getTransaction().begin();
        entityManager.merge(product);
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public boolean deleteProduct(long productId) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("DELETE FROM Product p WHERE p.id = :id");
        int deletedCount = query.setParameter("id", productId).executeUpdate();
        entityManager.getTransaction().commit();
        return deletedCount > 0;
    }

    @Override
    public Product getProduct(long productId) {
        return entityManager.find(Product.class, productId);
    }

    @Override
    public List<Product> getProducts() {
        List<Product> resultList = entityManager.createQuery("from Product", Product.class).getResultList();
        return resultList;
    }
}
