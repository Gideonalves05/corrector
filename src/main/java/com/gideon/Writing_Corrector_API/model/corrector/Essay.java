package com.gideon.Writing_Corrector_API.model.corrector;

import com.gideon.Writing_Corrector_API.model.user.UserModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "essays")
public class Essay {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private LocalDateTime submissionDate;

    @ManyToOne // Relacionamento muitos-para-um
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel userId;

    @Column(columnDefinition = "TEXT")
    private String feedback;
}