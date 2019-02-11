package com.kostenko.dao.impl;

import com.kostenko.dao.ClientDao;
import com.kostenko.domain.Client;

public class ClientDaoImpl implements ClientDao {

    @Override
    public boolean saveClient(Client client) {
        System.out.println("Saving.... Please wait");
        return true;
    }
}
