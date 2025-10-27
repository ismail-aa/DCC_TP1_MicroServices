package org.example.banqueservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCompteDto {

    private Integer id;
    private String nom;
    private String tel;
    private Double solde;


}