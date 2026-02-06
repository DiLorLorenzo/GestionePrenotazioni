package com.example.GestionePrenotazioni.services;

import com.example.GestionePrenotazioni.entities.Utente;
import com.example.GestionePrenotazioni.repositories.UtenteDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteService {

    private final UtenteDao utenteRepository;

    public UtenteService(UtenteDao utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    public Utente crea(Utente utente) {
        return utenteRepository.save(utente);
    }

    public Utente trovaPerUsername(String username) {
        return utenteRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("Utente non trovato: " + username));
    }

    public List<Utente> trovaUtenti() {
        return utenteRepository.findAll();
    }
}
