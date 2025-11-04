package com.davutcagri.Producer.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProduceService {

    @Value("${app.mq.exchange}")
    private String exchangeName;
    @Value("${app.mq.routing}")
    private String routingKey;
    private final RabbitTemplate rabbitTemplate;

    public ProduceService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void produceMessage(String message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
