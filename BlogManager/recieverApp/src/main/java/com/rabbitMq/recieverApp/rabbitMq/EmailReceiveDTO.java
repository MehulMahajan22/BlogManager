package com.rabbitMq.recieverApp.rabbitMq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailReceiveDTO {
    private String receiver,messageBody,subject;
}
