package com.example.rabbitproducer.controller;


import com.example.rabbitproducer.configuration.MQConfig;
import com.example.rabbitproducer.pojo.CustomMessage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class MessagePublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/publish")
    public String sendMessage(@RequestBody CustomMessage customMessage){
        customMessage.setMessageId(UUID.randomUUID().toString());
        customMessage.setMessageDate(new Date());
        rabbitTemplate.convertAndSend(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY, customMessage);

        return "Message Published";
    }

    @GetMapping("/messages")
    public List<CustomMessage> getAllMessages(){
        String request = "getMessages";
        List<CustomMessage> messages = null;

        try {
            Object response = rabbitTemplate.convertSendAndReceive(MQConfig.EXCHANGE, MQConfig.ROUTING_KEY2, request);

            if (response != null) {
                messages = (List<CustomMessage>) response;
            } else {
                // Handle the case where the response is not as expected
                System.err.println("Received unexpected response: " + response);
                messages = Collections.emptyList();
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during the messaging process
            System.err.println("Error while receiving messages: " + e.getMessage());
            e.printStackTrace();
            messages = Collections.emptyList();
        }

        return messages;
    }
}
