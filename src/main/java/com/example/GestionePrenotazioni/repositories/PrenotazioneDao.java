package com.example.GestionePrenotazioni.repositories;

import com.example.GestionePrenotazioni.entities.Prenotazione;
import com.example.GestionePrenotazioni.entities.Postazione;
import com.example.GestionePrenotazioni.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PrenotazioneDao extends JpaRepository<Prenotazione, UUID> {

    boolean existsByPostazioneAndData(Postazione postazione, LocalDate data);

    boolean existsByUtenteAndData(Utente utente, LocalDate data);

    List<Prenotazione> findByUtente_Username(String username);

    List<Prenotazione> findByPostazione_Id(UUID postazioneId);
}
