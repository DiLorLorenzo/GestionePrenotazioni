package com.example.GestionePrenotazioni.repositories;

import com.example.GestionePrenotazioni.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteDao extends JpaRepository<Utente, String> {

    boolean existsByEmail(String email);
}
