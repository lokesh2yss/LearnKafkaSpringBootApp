package com.codingshuttle.learnKafka.user_service.repositories;

import com.codingshuttle.learnKafka.user_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
