package com.vlsu.demo.model.repository;

import com.vlsu.demo.model.entity.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {
    Collection<Client> findAll();
}
