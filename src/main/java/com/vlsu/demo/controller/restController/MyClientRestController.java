package com.vlsu.demo.controller.restController;

import com.vlsu.demo.model.entity.Client;
import com.vlsu.demo.service.ClientService;
import com.vlsu.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyClientRestController {
    private ClientService clientService;
    private UserService userService;

    @Autowired
    public MyClientRestController(ClientService clientService, UserService userService) {
        this.clientService = clientService;
        this.userService = userService;
    }


    @PostMapping("/client/update")
    public void updateClient(String name, String surname, double height, double weight) {
        Client client = clientService.getClientByUserId(userService.getCurrentUserId());
        client.setName(name);
        client.setSurname(surname);
        client.setHeight(height);
        client.setWeight(weight);
        clientService.save(client);
    }
}
