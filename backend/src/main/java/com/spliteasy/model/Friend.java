package com.spliteasy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "friends")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    private String name;

    @Column(name = "paid_status", nullable = false)
    private boolean paidStatus = false;

    @Column(name = "session_id", nullable = false)
    private String sessionId;
}
