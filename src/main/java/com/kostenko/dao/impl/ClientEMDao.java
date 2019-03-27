package com.kostenko.dao.impl;

import com.kostenko.dao.ClientDao;
import com.kostenko.domain.Client;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
        return false;
    }

    @Override
    public boolean updateClient(Client client) {
        return false;
    }

    @Override
    public boolean deleteClient(long clientId) {
        return false;
    }

    @Override
    public Client getClient(long clientId) {
        return null;
    }

    @Override
    public List<Client> getAllClients() {
        entityManager.getTransaction().begin();
        List<Client> resultList = entityManager.createQuery("from Client", Client.class).getResultList();
        entityManager.getTransaction().commit();
        return resultList;
    }

    @Override
    public boolean phoneUsed(String phone) {
        return false;
    }
}
