package com.afkklein.rabbittutorialconsumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class FirstBasicConsumer {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = "FIRST-QUEUE-BASIC")
    public void receiveMessageFromFirstQueue(Message message) {
        log.info("received message from " + message.getMessageProperties().getConsumerQueue());
        String bodyAsString = message.getBody().toString();
        log.info("body " + bodyAsString);
    }
}
