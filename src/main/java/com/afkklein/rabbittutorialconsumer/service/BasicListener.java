package com.afkklein.rabbittutorialconsumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class BasicListener implements MessageListener {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onMessage(Message message) {
        log.info("received message from " + message.getMessageProperties().getConsumerQueue());
        String bodyAsString = message.getBody().toString();
        log.info("body " + bodyAsString);
    }
}
