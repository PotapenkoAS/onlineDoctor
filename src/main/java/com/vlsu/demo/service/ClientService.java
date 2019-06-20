package com.vlsu.demo.service;

import com.vlsu.demo.model.entity.Client;
import com.vlsu.demo.model.repository.ClientRepository;
import com.vlsu.demo.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private UserRepository ur;
    private ClientRepository cr;



    @Autowired
    public ClientService(UserRepository ur, ClientRepository cr) {
        this.ur = ur;
        this.cr = cr;
    }

    public Client getClientByUserId(int id) {
        return ur.findByUserId(id).getClientByUserId();
    }

    public void save(Client client) {
        cr.save(client);
    }

}
