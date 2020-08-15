package com.afkklein.rabbittutorialconsumer.service;

import com.afkklein.rabbittutorialconsumer.domain.Person;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JsonConsumer {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final MessageConverter messageConverter;

    @RabbitListener(queues = "JSON-QUEUE-BASIC")
    public void receiveMessageFromJsonQueue(Person person, Message message) {
        log.info("received message from " + message.getMessageProperties().getConsumerQueue());
        log.info("body " + person);
    }

}
