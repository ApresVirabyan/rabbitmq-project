package com.example.rabbitconsumer.handlers;

import com.example.rabbitconsumer.dto.CustomMessage;
import com.example.rabbitconsumer.pojo.CustomMessageEntity;
import com.example.rabbitconsumer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageHandler {

    @Autowired
    private MessageService messageService;

    public void handleMessage(CustomMessage message) {
        messageService.save(message);
    }

    public List<CustomMessage> getAllMessages() {
        System.err.println(" -------- MessageHandler ----------- ");
        return messageService.getAllMessages();
    }
}
