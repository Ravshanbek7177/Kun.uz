package com.example.kun_uz_.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tag")
public class TagEntity {
    @Id
    private Integer id;
    @Column(name = "name")
    private String name;
}
