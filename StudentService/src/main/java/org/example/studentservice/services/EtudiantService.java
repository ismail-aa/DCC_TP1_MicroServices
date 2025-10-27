package org.example.studentservice.services;

import org.example.studentservice.dto.RequestEtudiantDto;
import org.example.studentservice.dto.ResponseEtudiantDto;

import java.util.List;

public interface EtudiantService {

    ResponseEtudiantDto Add_Etudiant(RequestEtudiantDto requestEtudiantDto);
    List<ResponseEtudiantDto> GetAllEtudiants();
    ResponseEtudiantDto GetEtudiantById(Integer id);
    ResponseEtudiantDto Update_Etudiant(Integer id, RequestEtudiantDto requestEtudiantDto);
    void DeleteEtudiantById(Integer id);
}