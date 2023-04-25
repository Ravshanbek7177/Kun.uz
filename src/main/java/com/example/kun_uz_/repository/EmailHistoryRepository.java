package com.example.kun_uz_.repository;

import com.example.kun_uz_.entity.EmailHistoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailHistoryRepository extends CrudRepository<EmailHistoryEntity , Integer> {

    Optional<EmailHistoryEntity> findByEmail(String email);
}
