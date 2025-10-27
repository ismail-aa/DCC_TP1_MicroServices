package org.example.tp1.controllers;

import org.example.tp1.entities.Nombres;
import org.example.tp1.services.CalculService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
    @RequestMapping("v1/calculs")
public class CalculateurController {

    private CalculService calculService;
    public CalculateurController(CalculService calculService) {
        this.calculService = calculService;
    }

    @PostMapping
    public ResponseEntity<Double> calcul_somme(@RequestBody Nombres nombres) {
        Double somme = calculService.somme(nombres);
        if(nombres!=null) return ResponseEntity.ok(somme);

        return ResponseEntity.internalServerError().build();
    }
    
}