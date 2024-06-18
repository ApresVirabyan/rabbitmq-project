package com.example.rabbitconsumer.repository;

import com.example.rabbitconsumer.pojo.CustomMessageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<CustomMessageEntity, String> {
}
