package com.codingshuttle.learnKafka.user_service.services;

import com.codingshuttle.learnKafka.user_service.dtos.CreateUserRequestDTO;
import com.codingshuttle.learnKafka.user_service.entities.User;
import com.codingshuttle.learnKafka.user_service.events.UserCreatedEvent;
import com.codingshuttle.learnKafka.user_service.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final KafkaTemplate<Long, UserCreatedEvent> kafkaTemplate;

    @Value("${kafka.topic.user-created-topic}")
    private String KAFKA_USER_CREATED_TOPIC;
    public void createUser(CreateUserRequestDTO createUserRequestDTO) {
        User user = modelMapper.map(createUserRequestDTO, User.class);
        User savedUser = userRepository.save(user);
        UserCreatedEvent userCreatedEvent = modelMapper.map(savedUser, UserCreatedEvent.class);
        kafkaTemplate.send(KAFKA_USER_CREATED_TOPIC, userCreatedEvent.getId(), userCreatedEvent);
    }
}
