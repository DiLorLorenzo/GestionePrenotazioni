package com.example.GestionePrenotazioni.services;

import com.example.GestionePrenotazioni.entities.Edificio;
import com.example.GestionePrenotazioni.repositories.EdificioDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EdificioService {

    public final EdificioDao edificioDao;

    public EdificioService(EdificioDao edificioDao) {
        this.edificioDao = edificioDao;
    }


    public Edificio creaEdificio(Edificio edificio) {
        return edificioDao.save(edificio);
    }


    public Edificio trovaPerId(UUID id) {
        return edificioDao.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Edificio non trovato con id: " + id)
                );
    }


    public List<Edificio> trovaTutti() {
        return edificioDao.findAll();
    }



}
