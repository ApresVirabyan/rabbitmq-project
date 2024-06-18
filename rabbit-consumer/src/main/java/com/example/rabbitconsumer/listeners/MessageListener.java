package com.example.rabbitconsumer.listeners;


import com.example.rabbitconsumer.configuration.MQConfig;
import com.example.rabbitconsumer.dto.CustomMessage;
import com.example.rabbitconsumer.handlers.MessageHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageListener {

    @Autowired
    private MessageHandler messageHandler;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(CustomMessage customMessage){
        System.out.println(customMessage);
        messageHandler.handleMessage(customMessage);
    }

    @RabbitListener(queues = MQConfig.QUEUE2)
    public List<CustomMessage> getAllMessages(String request){

        if("getMessages".equals(request)){
           return messageHandler.getAllMessages();
        }

        return null;
    }
}
