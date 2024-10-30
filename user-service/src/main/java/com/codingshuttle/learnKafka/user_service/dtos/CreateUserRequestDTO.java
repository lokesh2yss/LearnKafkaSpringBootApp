package com.codingshuttle.learnKafka.user_service.dtos;

import lombok.Data;

@Data
public class CreateUserRequestDTO {
    private Long id;
    private String name;
    private String email;
}
