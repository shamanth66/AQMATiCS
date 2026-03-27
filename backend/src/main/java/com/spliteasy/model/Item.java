package com.spliteasy.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "items")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Item name is required")
    @Column(nullable = false)
    private String name;

    @NotNull @DecimalMin(value = "0.01", message = "Price must be positive")
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "session_id", nullable = false)
    private String sessionId;

    @ManyToMany
    @JoinTable(
        name = "item_participants",
        joinColumns = @JoinColumn(name = "item_id"),
        inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    @Builder.Default
    private Set<Friend> participants = new HashSet<>();
}
