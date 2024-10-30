package com.codingshuttle.learnKafka.user_service.events;

import lombok.Data;

@Data
public class UserCreatedEvent {
    private Long id;
    private String email;
}
