package com.vlsu.demo.model.repository;

import com.vlsu.demo.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    boolean existsByLoginIs(String login);

    boolean existsByLoginAndPassword(String login, String password);

    User findByLogin(String login);

    User findByUserId(int id);

}
