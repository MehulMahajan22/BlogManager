package com.rabbitMq.recieverApp.rabbitMq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MailConsumer {
    @RabbitListener(queues = "mail_queue_blogApp")
    public void getTheDto(EmailReceiveDTO emailDTO){
        System.out.println(emailDTO.getReceiver());
        System.out.println(emailDTO.getMessageBody());
        System.out.println(emailDTO.getSubject());
    }
}
