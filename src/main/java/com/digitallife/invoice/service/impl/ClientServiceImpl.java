package com.digitallife.invoice.service.impl;

import com.digitallife.invoice.dao.inter.ClientDaoInter;
import com.digitallife.invoice.entity.Client;
import com.digitallife.invoice.service.inter.ClientServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientServiceInter {

    @Autowired
    @Qualifier("clientDao")
    private ClientDaoInter clientDaoInter;

    @Override
    public List<Client> getAllClients() {
        return clientDaoInter.getAllClients();
    }

    @Override
    public Client getClientById(int id) {
        return clientDaoInter.getClientById(id);
    }

    @Override
    public Integer addClient(Client client) {
        return clientDaoInter.addClient(client);
    }

    @Override
    public boolean updateClient(Client client) {
        return clientDaoInter.updateClient(client);
    }

    @Override
    public boolean deleteClient(int id) {
        return clientDaoInter.deleteClient(id);
    }
}
