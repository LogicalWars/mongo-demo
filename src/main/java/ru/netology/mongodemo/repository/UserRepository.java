package ru.netology.mongodemo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.netology.mongodemo.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByName(String name);
}