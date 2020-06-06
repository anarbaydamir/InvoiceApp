package com.digitallife.invoice.dao.impl;

import com.digitallife.invoice.dao.inter.ClientDaoInter;
import com.digitallife.invoice.entity.Client;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("clientDao")
public class ClientDaoImpl implements ClientDaoInter {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Client> getAllClients() {
        String jpql = "Select c from Client c";

        Query query = entityManager.createQuery(jpql,Client.class);
        List<Client> clients = query.getResultList();

        return clients;
    }

    @Override
    public Client getClientById(int id) {
        Client client = entityManager.find(Client.class,id);

        return client;
    }

    @Override
    public Integer addClient(Client client) {
        entityManager.persist(client);
        entityManager.flush();

        return client.getId();
    }

    @Override
    public boolean updateClient(Client client) {
        try {
            entityManager.merge(client);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteClient(int id) {
        try{
            Client client = entityManager.find(Client.class,id);
            entityManager.remove(client);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
}
