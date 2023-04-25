package com.example.kun_uz_.dto.EmailHistory;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EmailHistoryDTO {
    private Integer Id;
    private String message;
    private String email;

}
