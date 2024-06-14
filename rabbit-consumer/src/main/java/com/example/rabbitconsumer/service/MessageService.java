package com.example.rabbitconsumer.service;


import com.example.rabbitconsumer.dto.CustomMessageDto;
import com.example.rabbitconsumer.pojo.CustomMessage;
import com.example.rabbitconsumer.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void save(CustomMessage message){
        log.info("Persisting message: {}", message);

//        CustomMessage message = convert(messageDto);
        messageRepository.save(message);

        log.info("Done persisting message: {}", message);
    }

    private CustomMessage convert(CustomMessageDto customMessageDto){
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessageId(customMessageDto.getMessageId());
        customMessage.setMessage(customMessageDto.getMessage());
        customMessage.setMessageDate(customMessageDto.getMessageDate());
        return customMessage;
    }

}
