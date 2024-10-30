package com.codingshuttle.learnKafka.user_service.controllers;

import com.codingshuttle.learnKafka.user_service.dtos.CreateUserRequestDTO;
import com.codingshuttle.learnKafka.user_service.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/users")
public class UserController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final UserService userService;

    @Value("${kafka.topic.user-random-topic}")
    private String KAFKA_USER_RANDOM_TOPIC;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequestDTO createUserRequestDTO) {
        userService.createUser(createUserRequestDTO);
        return ResponseEntity.ok("User created Successfully");
    }
    @PostMapping(path = "/{message}")
    public ResponseEntity<String> sendMessage(@PathVariable String message) {
        for (int i = 0; i<1000; i++) {
            kafkaTemplate.send(KAFKA_USER_RANDOM_TOPIC, ""+i%3, message+i);
        }
        return ResponseEntity.ok("Message queued");
    }
}
