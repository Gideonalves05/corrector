package com.gideon.Writing_Corrector_API.model.essay;

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
    @GeneratedValue(strategy = GenerationType.UUID) // Gera um UUID automaticamente
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT") // Para suportar textos longos
    private String content;

    @Column(nullable = false)
    private LocalDateTime submissionDate;

    @ManyToOne // Relacionamento muitos-para-um
    @JoinColumn(name = "user_id", nullable = false) // Cria a coluna de chave estrangeira
    private UserModel user;
}
