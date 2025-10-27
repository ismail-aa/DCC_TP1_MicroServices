package org.example.banqueservice.service;

import org.example.banqueservice.dto.RequestCompteDto;
import org.example.banqueservice.dto.ResponseCompteDto;
import org.example.banqueservice.entities.Compte;
import org.example.banqueservice.mappers.CompteMapper;
import org.example.banqueservice.repository.CompteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompteServiceImpl implements CompteService {

    private CompteRepository compteRepository;
    private CompteMapper compteMapper;

    public CompteServiceImpl(CompteRepository compteRepository, CompteMapper compteMapper) {
        this.compteRepository = compteRepository;
        this.compteMapper = compteMapper;
    }

    @Override
    public ResponseCompteDto Add_Compte(RequestCompteDto requestCompteDto) {
        Compte compte = compteMapper.DTO_to_Entity(requestCompteDto);
        Compte saved_compte = compteRepository.save(compte);
        return compteMapper.Entity_to_DTO(saved_compte);
    }

    @Override
    public List<ResponseCompteDto> GetAllCompte() {
        List<Compte> comptes = compteRepository.findAll();
        List<ResponseCompteDto> compteDtos = new ArrayList<>(); // vide dés le début

        for (Compte c : comptes) {
            compteDtos.add(compteMapper.Entity_to_DTO(c));
        }

        return compteDtos;
    }

    @Override
    public ResponseCompteDto GetCompteById(Integer id) {
        Compte compte = compteRepository.findById(id).orElseThrow();

        return compteMapper.Entity_to_DTO(compte);
    }

    @Override
    public ResponseCompteDto Update_Compte(Integer id, RequestCompteDto requestCompteDto) {
        Compte new_compte = compteMapper.DTO_to_Entity(requestCompteDto);

        Compte compte = compteRepository.findById(id).orElseThrow();

        if (new_compte.getNom()!=null) compte.setNom(new_compte.getNom());
        if (new_compte.getTel()!=null) compte.setTel(new_compte.getTel());
        if (new_compte.getSolde()!=null) compte.setSolde(new_compte.getSolde());

        Compte saved_compte = compteRepository.save(compte);

        return compteMapper.Entity_to_DTO(saved_compte);
    }

    @Override
    public void DeleteCompteById(Integer id) {
        compteRepository.deleteById(id);
    }

    @Override
    public ResponseCompteDto Crediter(Integer id, Double m) {
        Compte compte = compteRepository.findById(id).orElseThrow();

        compte.setSolde(compte.getSolde() + m);
        Compte saved = compteRepository.save(compte);
        return compteMapper.Entity_to_DTO(saved);
    }

    @Override
    public ResponseCompteDto Debiter(Integer id, Double m) {
        Compte compte = compteRepository.findById(id).orElseThrow();

        if (compte.getSolde() >= m) {
            compte.setSolde(compte.getSolde() - m);
        }

        Compte saved = compteRepository.save(compte);
        return compteMapper.Entity_to_DTO(saved);
    }


}