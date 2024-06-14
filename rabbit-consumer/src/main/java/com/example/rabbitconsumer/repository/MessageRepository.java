package com.example.rabbitconsumer.repository;

import com.example.rabbitconsumer.pojo.CustomMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<CustomMessage, String> {
}
