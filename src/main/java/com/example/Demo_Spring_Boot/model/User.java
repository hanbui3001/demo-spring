package com.example.Demo_Spring_Boot.model;

import com.example.Demo_Spring_Boot.other.Status;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique = true,  nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String displayName;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private Status status;
}
