package org.example.banqueservice.mappers;

import org.example.banqueservice.dto.RequestCompteDto;
import org.example.banqueservice.dto.ResponseCompteDto;
import org.example.banqueservice.entities.Compte;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CompteMapper {

    public Compte DTO_to_Entity(RequestCompteDto requestCompteDto)
    {
        Compte compte = new Compte();
        BeanUtils.copyProperties(requestCompteDto,compte);
        return compte;
    }

    public ResponseCompteDto Entity_to_DTO(Compte compte)
    {
        ResponseCompteDto responseCompteDto = new ResponseCompteDto();
        BeanUtils.copyProperties(compte,responseCompteDto);
        return responseCompteDto;
    }


}