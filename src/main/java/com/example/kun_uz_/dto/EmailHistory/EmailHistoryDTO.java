package com.example.kun_uz_.dto.EmailHistory;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EmailHistoryDTO {
    @NotNull(message = "ID  required")
    private Integer Id;
    @NotNull(message = "Message  required")
    @Size(min = 10, max = 250 , message = "Message must be between 10 and 250 character")
    private String message;
    @NotNull(message = "email required")
    private String email;

}
