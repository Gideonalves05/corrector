
package com.gideon.Writing_Corrector_API.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "users")
public class UserModel {


    @Id
    @GeneratedValue(generator = "user_id")
    @Column(unique = true)
    private Long userId = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @CreationTimestamp
    private LocalDateTime createdTa;
}
