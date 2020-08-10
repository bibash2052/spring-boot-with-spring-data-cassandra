package com.cassandra.demo.demo.entity;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@Table
@ToString
public class Student {
    @PrimaryKey
    private UUID id;

    private String name;

    private String address;

    private boolean status;

    public Student() {

    }

    public Student(UUID randomUUID, String name, String address, boolean status) {
         id = randomUUID;
         this.name = name;
         this.address = address;
         this.status = status;
    }
}
