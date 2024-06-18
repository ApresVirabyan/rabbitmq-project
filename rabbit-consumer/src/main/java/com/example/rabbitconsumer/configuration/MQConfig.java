package com.example.rabbitconsumer.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class MQConfig {

    public static final String QUEUE = "message_queue";
    public static final String QUEUE2 = "message_queue2";

    public static final String EXCHANGE = "message_exchange";
    public static final String EXCHANGE2 = "message_exchange2";

    public static final String ROUTING_KEY = "message_routingKey";
    public static final String ROUTING_KEY2= "message_routingKey2";

    @Value("${rabbitmq.queue}")
    private String queueName;

    @Value("${rabbitmq.queueRes}")
    private String queueRes;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.exchangeRes}")
    private String exchangeRes;

    @Value("${rabbitmq.routingkey}")
    private String routingKey;

    @Value("${rabbitmq.routingkeyRes}")
    private String routingKeyRes;

    @Value("${rabbitmq.username}")
    private String username;

    @Value("${rabbitmq.password}")
    private String password;

    @Value("${rabbitmq.host}")
    private String host;

    @Bean
    public Queue queue(){
        return new Queue(queueName, false);
    }

    @Bean
    public Queue queue2(){
        return new Queue(queueRes, false);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public TopicExchange exchange2(){
        return new TopicExchange(exchangeRes);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    @Bean
    public Binding binding2(Queue queue, TopicExchange exchange){
        return BindingBuilder.bind(queue2()).to(exchange2()).with(routingKeyRes);
    }

    @Bean
    public MessageConverter messageConverter() {
        return  new Jackson2JsonMessageConverter();
    }

    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate amqpTemplate(){
        CachingConnectionFactory connectionFactory = connectionFactory();
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setReplyTimeout(5000);
        return rabbitTemplate;
    }
}
