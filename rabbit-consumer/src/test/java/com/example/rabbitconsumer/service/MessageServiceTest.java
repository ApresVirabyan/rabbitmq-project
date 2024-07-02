package com.example.rabbitconsumer.service;

import com.example.rabbitconsumer.dto.CustomMessage;
import com.example.rabbitconsumer.pojo.CustomMessageEntity;
import com.example.rabbitconsumer.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageService messageService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave(){

        CustomMessage message = new CustomMessage();
        message.setMessageId("1");
        message.setMessage("Test message");
        message.setMessageDate(new Date());

        CustomMessageEntity entity = new CustomMessageEntity();
        entity.setMessageId("1");
        entity.setMessage("Test message");
        entity.setMessageDate(new Date());

        when(messageRepository.save(any(CustomMessageEntity.class))).thenReturn(entity);

        messageService.save(message);
        verify(messageRepository, times(1)).save(any(CustomMessageEntity.class));

    }

    @Test
    public void testGetAllMessages(){
        CustomMessageEntity entity1 = new CustomMessageEntity();
        entity1.setMessageId("1");
        entity1.setMessage("Message 1");
        entity1.setMessageDate(new Date());

        CustomMessageEntity entity2 = new CustomMessageEntity();
        entity2.setMessageId("2");
        entity2.setMessage("Message 2");
        entity2.setMessageDate(new Date());

        List<CustomMessageEntity> entities = Arrays.asList(entity1, entity2);

        when(messageRepository.findAll()).thenReturn(entities);

        List<CustomMessage> allMessages = messageService.getAllMessages();

        assertEquals(2, allMessages.size());
        assertEquals("Message 1", allMessages.get(0).getMessage());
        assertEquals("Message 2", allMessages.get(1).getMessage());
    }
}
