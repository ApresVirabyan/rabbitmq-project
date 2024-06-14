package com.example.rabbitconsumer.handlers;

import com.example.rabbitconsumer.dto.CustomMessageDto;
import com.example.rabbitconsumer.pojo.CustomMessage;
import com.example.rabbitconsumer.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler {

    @Autowired
    private MessageService messageService;

    public void handleMessage(CustomMessage message) {
        messageService.save(message);
    }
}
