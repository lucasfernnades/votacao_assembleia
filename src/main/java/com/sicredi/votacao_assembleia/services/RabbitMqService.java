package com.sicredi.votacao_assembleia.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String nameQueue, Object message) {
        rabbitTemplate.convertAndSend(nameQueue, message);
    }
}
