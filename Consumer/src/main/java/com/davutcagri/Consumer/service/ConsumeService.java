package com.davutcagri.Consumer.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumeService {

    @RabbitListener(queues = "test.queue")
    public void recieveMessage(String message) {
        System.out.println("MESSAGE RECIEVED: " + message);
    }

}
