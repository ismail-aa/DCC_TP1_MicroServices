package org.example.studentservice.services;

import org.example.studentservice.dto.RequestEtudiantDto;
import org.example.studentservice.dto.ResponseEtudiantDto;
import org.example.studentservice.entities.Etudiant;
import org.example.studentservice.mapper.EtudiantMapper;
import org.example.studentservice.modele.Filiere;
import org.example.studentservice.repository.EtudiantRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class EtudiantServiceImpl implements EtudiantService {

    private EtudiantRepository etudiantRepository;
    private EtudiantMapper etudiantMapper;
    private RestTemplate restTemplate;

    public EtudiantServiceImpl(EtudiantRepository etudiantRepository, EtudiantMapper etudiantMapper) {
        this.etudiantRepository = etudiantRepository;
        this.etudiantMapper = etudiantMapper;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public ResponseEtudiantDto Add_Etudiant(RequestEtudiantDto requestEtudiantDto) {
        Etudiant etudiant = etudiantMapper.DTO_to_Entity(requestEtudiantDto);
        Etudiant saved_etudiant = etudiantRepository.save(etudiant);
        return etudiantMapper.Entity_to_DTO(saved_etudiant);
    }

    @Override
    public List<ResponseEtudiantDto> GetAllEtudiants() {
        List<Etudiant> etudiants = etudiantRepository.findAll();
        List<ResponseEtudiantDto> etudiantDtos = new ArrayList<>();

        for (Etudiant e : etudiants) {
            etudiantDtos.add(etudiantMapper.Entity_to_DTO(e));
        }

        return etudiantDtos;
    }

    @Override
    public ResponseEtudiantDto GetEtudiantById(Integer id) {
        Etudiant etudiant = etudiantRepository.findById(id).orElseThrow();

        return etudiantMapper.Entity_to_DTO(etudiant);
    }

    @Override
    public ResponseEtudiantDto Update_Etudiant(Integer id, RequestEtudiantDto requestEtudiantDto) {
        Etudiant new_etudiant = etudiantMapper.DTO_to_Entity(requestEtudiantDto);

        Etudiant etudiant = etudiantRepository.findById(id).orElseThrow();

        if (new_etudiant.getNom() != null) etudiant.setNom(new_etudiant.getNom());
        if (new_etudiant.getPrenom() != null) etudiant.setPrenom(new_etudiant.getPrenom());
        if (new_etudiant.getCne() != null) etudiant.setCne(new_etudiant.getCne());

        Etudiant saved_etudiant = etudiantRepository.save(etudiant);
        return etudiantMapper.Entity_to_DTO(saved_etudiant);
    }

    @Override
    public void DeleteEtudiantById(Integer id) {
        etudiantRepository.deleteById(id);
    }
}