package com.gideon.Writing_Corrector_API.model.corrector;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


@Entity(name = "correction")
public class Correction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idCorrection;

    private String analysis;

    private int firstCompetenceScore;

    private int secondCompetenceScore;

    private int thirdCompetenceScore;

    private int fourthCompetenceScore;

    private int fifthCompetenceScore;

    private int finalScore;


}
