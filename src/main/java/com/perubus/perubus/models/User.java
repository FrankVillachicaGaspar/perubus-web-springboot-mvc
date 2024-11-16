package com.perubus.perubus.models;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String email;

    @NotNull
    private String name;

    @NotNull
    private String lastname;

    @NotNull
    private String dni;

    @NotNull
    private String phone;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @CreationTimestamp
    private LocalDateTime updatedOn;
}
