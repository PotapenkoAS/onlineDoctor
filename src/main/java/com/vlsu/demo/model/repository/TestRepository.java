package com.vlsu.demo.model.repository;

import com.vlsu.demo.model.entity.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TestRepository extends CrudRepository<Test,Integer> {
    Collection<Test> findAll();
    Collection<Test> findAllByUserIdOrderByDateDesc(int userId);
}
