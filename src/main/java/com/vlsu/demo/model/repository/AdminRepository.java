package com.vlsu.demo.model.repository;


import com.vlsu.demo.model.entity.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface AdminRepository extends CrudRepository<Admin,Integer> {
    Collection<Admin> findAll();
}
