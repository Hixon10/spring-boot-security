package ru.hixon.microservice.repository;

import org.springframework.stereotype.Service;
import ru.hixon.microservice.entity.User;

@Service
public class UserRepository {

    public boolean existsByUsername(String username) {
        return findByUsername(username) != null;
    }

    public User findByUsername(String username) {
        return null;
    }

    public void deleteByUsername(String username) {

    }

    public void save(User user) {

    }
}
