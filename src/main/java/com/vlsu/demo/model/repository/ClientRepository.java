package com.vlsu.demo.model.repository;

import com.vlsu.demo.model.entity.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ClientRepository extends CrudRepository<Client, Integer> {
    Collection<Client> findAll();
}
