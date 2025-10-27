package org.example.studentservice.modele;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Filiere {

    private Integer idFiliere;
    private String code;
    private String libelle;

}