package com.vlsu.demo.model.repository;

import com.vlsu.demo.model.entity.Test;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface TestRepository extends CrudRepository<Test,Integer> {
    Collection<Test> findAll();
    Collection<Test> findAllByUserIdOrderByDateDesc(int userId);
}
