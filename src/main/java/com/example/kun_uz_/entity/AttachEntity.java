package com.example.kun_uz_.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "attach")
@Entity
public class AttachEntity {  // Bo'ldi
    @Id
    @GeneratedValue(strategy =GenerationType.UUID )
    private String id;
    @Column(name = "original_name")
    private String originalName;
    @Column
    private String path;
    @Column
    private Long size;
    @Column
    private String extension;
    @Column(name = "created_date")
    private LocalDateTime createdData;
}
