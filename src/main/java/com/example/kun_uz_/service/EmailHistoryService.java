package com.example.kun_uz_.service;

import com.example.kun_uz_.dto.EmailHistory.EmailHistoryDTO;
import com.example.kun_uz_.dto.ProfileDto.ProfileDTO;
import com.example.kun_uz_.entity.EmailHistoryEntity;
import com.example.kun_uz_.exps.AppBadRequestException;
import com.example.kun_uz_.repository.EmailHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
@AllArgsConstructor
public class EmailHistoryService {

    @Autowired
    private EmailHistoryRepository repository;
    public EmailHistoryDTO create(EmailHistoryDTO dto) {
        Optional<EmailHistoryEntity> optional = repository.findByEmail(dto.getEmail());
        if (optional.isPresent()) {
            throw new AppBadRequestException(" bu foydalanuvchi mavjud");
        }
        EmailHistoryEntity entity = new EmailHistoryEntity();
        entity.setMessage(dto.getMessage());
        entity.setEmail(dto.getEmail());
        entity.setCreatedDate(LocalDateTime.now());
        repository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }




}
