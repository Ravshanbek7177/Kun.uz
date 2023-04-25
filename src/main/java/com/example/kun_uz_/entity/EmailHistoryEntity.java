package com.example.kun_uz_.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "emailHistory")
public class EmailHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(name = "message")
    private String message;
    @Column(name = "email")
    private String email;

    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
