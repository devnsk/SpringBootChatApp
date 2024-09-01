package com.nsk.websocket.repository;

import com.nsk.websocket.entity.Status;
import com.nsk.websocket.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {
    List<User> findAllByStatus(Status status);
}
