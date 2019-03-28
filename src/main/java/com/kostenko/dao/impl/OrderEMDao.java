package com.kostenko.dao.impl;

import com.kostenko.dao.OrderDao;
import com.kostenko.domain.Order;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

@Component
public class OrderEMDao implements OrderDao {

    EntityManager entityManager;

    public OrderEMDao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit");
        entityManager = factory.createEntityManager();
    }

    @Override
    public boolean saveOrder(Order order) {
        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public boolean orderFound(long orderId) {
        Order order = entityManager.find(Order.class, orderId);
        return order != null;
    }

    @Override
    public boolean deleteOrder(long orderId) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("DELETE FROM Order o WHERE o.id = :id");
        int deletedCount = query.setParameter("id", orderId).executeUpdate();
        entityManager.getTransaction().commit();
        return deletedCount > 0;
    }

    @Override
    public boolean updateOrder(Order order) {
        entityManager.getTransaction().begin();
        entityManager.merge(order);
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public Order getOrder(long orderId) {
        return entityManager.find(Order.class, orderId);
    }

    @Override
    public List<Order> getOrders() {
        List<Order> resultList = entityManager.createQuery("from Order", Order.class).getResultList();
        return resultList;
    }

    @Override
    public List<Order> getOrders(long clientId) {
        List<Order> resultList = entityManager.createQuery("SELECT o from Order o WHERE o.client.id = :clientId", Order.class)
                .setParameter("clientId", clientId).getResultList();
        return resultList;
    }
}
