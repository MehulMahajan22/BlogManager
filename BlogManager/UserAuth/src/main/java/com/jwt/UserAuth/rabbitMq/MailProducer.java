package com.jwt.UserAuth.rabbitMq;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MailProducer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private DirectExchange directExchange;

    public void sendMailToQueue(EmailDTO emailDTO){rabbitTemplate.convertAndSend(directExchange.getName(),"mail_routing_blogApp",emailDTO);}
}
