package com.vlsu.demo.model.repository;


import com.vlsu.demo.model.entity.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface AdminRepository extends CrudRepository<Admin,Integer> {
    Collection<Admin> findAll();
}
