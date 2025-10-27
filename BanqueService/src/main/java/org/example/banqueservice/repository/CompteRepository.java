package org.example.banqueservice.repository;

import org.example.banqueservice.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte,Integer> {

}