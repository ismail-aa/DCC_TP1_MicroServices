package org.example.banqueservice.service;

import org.example.banqueservice.dto.RequestCompteDto;
import org.example.banqueservice.dto.ResponseCompteDto;

import java.util.List;

public interface CompteService {

    public ResponseCompteDto Add_Compte(RequestCompteDto requestCompteDto);
    public List<ResponseCompteDto> GetAllCompte();
    public ResponseCompteDto GetCompteById(Integer id);
    public ResponseCompteDto Update_Compte(Integer id,RequestCompteDto requestCompteDto);
    public void DeleteCompteById(Integer id);
    public ResponseCompteDto Crediter(Integer id,Double n);
    public ResponseCompteDto Debiter(Integer id,Double n);

}