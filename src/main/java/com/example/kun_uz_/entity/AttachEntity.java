package com.example.kun_uz_.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "attach")
@Entity
public class AttachEntity {  // Bo'ldi
    @Id
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
