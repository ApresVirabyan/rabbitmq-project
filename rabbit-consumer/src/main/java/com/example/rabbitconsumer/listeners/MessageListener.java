package com.example.rabbitconsumer.listeners;


import com.example.rabbitconsumer.configuration.MQConfig;
import com.example.rabbitconsumer.dto.CustomMessageDto;
import com.example.rabbitconsumer.handlers.MessageHandler;
import com.example.rabbitconsumer.pojo.CustomMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @Autowired
    private MessageHandler messageHandler;

    @RabbitListener(queues = MQConfig.QUEUE)
    public void listener(CustomMessage customMessage){
        System.out.println(customMessage);
        messageHandler.handleMessage(customMessage);
    }
}
