package com.kostenko.dao.impl;

import com.kostenko.dao.ClientDao;
import com.kostenko.domain.Client;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class ClientEMDao implements ClientDao {

    private EntityManager entityManager;

    public ClientEMDao() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit");
        this.entityManager = factory.createEntityManager();
    }

    @Override
    public boolean saveClient(Client client) {
        entityManager.getTransaction().begin();
        entityManager.persist(client);
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public boolean clientFound(long clientId) {
        Client client = entityManager.find(Client.class, clientId);
        return client != null;
    }

    @Override
    public boolean updateClient(Client client) {
        entityManager.getTransaction().begin();
        entityManager.merge(client);
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public boolean deleteClient(long clientId) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("DELETE FROM Client c WHERE c.id = :id");
        int deletedCount = query.setParameter("id", clientId).executeUpdate();
        entityManager.getTransaction().commit();
        return deletedCount > 0;
    }

    @Override
    public Client getClient(long clientId) {
        return entityManager.find(Client.class, clientId);
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> resultList = entityManager.createQuery("from Client", Client.class).getResultList();
        return resultList;
    }

    @Override
    public boolean phoneUsed(String phone) {
        Query query = entityManager.createQuery("SELECT c FROM Client c WHERE c.phone = :phone");
        List resultList = query.setParameter("phone", phone).getResultList();
        return resultList.size() > 0;
    }
}
