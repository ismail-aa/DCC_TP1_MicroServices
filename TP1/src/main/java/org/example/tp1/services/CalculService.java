package org.example.tp1.services;

import org.example.tp1.entities.Nombres;
import org.springframework.stereotype.Service;

@Service
public class CalculService {

    public Double somme(Nombres nombres) {
        return nombres.getA() + nombres.getB();
    }


}
