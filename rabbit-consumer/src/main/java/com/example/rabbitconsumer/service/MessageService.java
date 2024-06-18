package com.example.rabbitconsumer.service;


import com.example.rabbitconsumer.dto.CustomMessage;
import com.example.rabbitconsumer.pojo.CustomMessageEntity;
import com.example.rabbitconsumer.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void save(CustomMessage message){
        log.info("Persisting message: {}", message);
        CustomMessageEntity entity = convertToEntity(message);
        messageRepository.save(entity);

        log.info("Done persisting message: {}", message);
    }

    public List<CustomMessage> getAllMessages(){
        List<CustomMessage> messages = messageRepository.findAll()
                .stream().map(this::convert)
                .collect(Collectors.toList());
        log.info("Getting all messages " + messages);
        return messages;
    }

    private CustomMessage convert(CustomMessageEntity customMessageEntity){
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessageId(customMessageEntity.getMessageId());
        customMessage.setMessage(customMessageEntity.getMessage());
        customMessage.setMessageDate(customMessageEntity.getMessageDate());
        return customMessage;
    }

    private CustomMessageEntity convertToEntity(CustomMessage customMessage){
        CustomMessageEntity customMessageEntity = new CustomMessageEntity();
        customMessageEntity.setMessageId(customMessage.getMessageId());
        customMessageEntity.setMessage(customMessage.getMessage());
        customMessageEntity.setMessageDate(customMessage.getMessageDate());
        return customMessageEntity;
    }

}

