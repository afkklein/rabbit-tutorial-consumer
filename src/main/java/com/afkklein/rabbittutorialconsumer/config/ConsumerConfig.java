package com.afkklein.rabbittutorialconsumer.config;

import com.afkklein.rabbittutorialconsumer.service.BasicListener;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ConsumerConfig {
    private final ConnectionFactory connectionFactory;
    private final BasicListener basicListener;
    private final SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory;

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Bean
    public Queue queueBasic() {
        return QueueBuilder
                .durable("FIRST-QUEUE-BASIC")
                .build();
    }
    
    @Bean
    public MessageListenerContainer listenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("SECOND-QUEUE-BASIC");
        container.setMessageListener(basicListener); //atenção aqui
        container.setAdviceChain(simpleRabbitListenerContainerFactory.getAdviceChain());
        container.start();
        return container;
    }

    @RabbitListener(queues = "SECOND-QUEUE-BASIC")
    public void receiveMessageFromTopic1(Message message) {
        log.info("receive message from " + message.getMessageProperties().getConsumerQueue());
        String bodyAsString = message.getBody().toString();
        log.info("body " + bodyAsString);
    }
}
