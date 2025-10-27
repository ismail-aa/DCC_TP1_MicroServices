package org.example.studentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.studentservice.modele.Filiere;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestEtudiantDto {
    private String nom;
    private String prenom;
    private String cne;
    private Filiere filiere;
}