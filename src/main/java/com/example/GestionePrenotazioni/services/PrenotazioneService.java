package com.example.GestionePrenotazioni.services;

import com.example.GestionePrenotazioni.entities.Prenotazione;
import com.example.GestionePrenotazioni.entities.Postazione;
import com.example.GestionePrenotazioni.entities.Utente;
import com.example.GestionePrenotazioni.repositories.PostazioneDao;
import com.example.GestionePrenotazioni.repositories.PrenotazioneDao;
import com.example.GestionePrenotazioni.repositories.UtenteDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PrenotazioneService {

    private final PrenotazioneDao prenotazioneDao;
    private final UtenteDao utenteDao;
    private final PostazioneDao postazioneDao;

    public PrenotazioneService(PrenotazioneDao prenotazioneDao,
                               UtenteDao utenteDao,
                               PostazioneDao postazioneDao) {
        this.prenotazioneDao = prenotazioneDao;
        this.utenteDao = utenteDao;
        this.postazioneDao = postazioneDao;
    }


    public Prenotazione trovaPerId(UUID id) {
        return prenotazioneDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Prenotazione non trovata con id: " + id));
    }


    public List<Prenotazione> trovaTutte() {
        return prenotazioneDao.findAll();
    }


    public List<Prenotazione> trovaPerUtente(String username) {
        return prenotazioneDao.findByUtente_Username(username);
    }


    public List<Prenotazione> trovaPerPostazione(UUID postazioneId) {
        return prenotazioneDao.findByPostazione_Id(postazioneId);
    }



    public Prenotazione prenota(String username, UUID postazioneId, LocalDate data) {

        Utente utente = utenteDao.findById(username)
                .orElseThrow(() -> new RuntimeException("Utente non trovato: " + username));

        Postazione postazione = postazioneDao.findById(postazioneId)
                .orElseThrow(() -> new RuntimeException("Postazione non trovata: " + postazioneId));


        if (prenotazioneDao.existsByUtenteAndData(utente, data)) {
            throw new RuntimeException("Non puoi prenotare più di una postazione per la data: " + data);
        }


        if (prenotazioneDao.existsByPostazioneAndData(postazione, data)) {
            throw new RuntimeException("Postazione già prenotata per la data: " + data);
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setData(data);
        prenotazione.setUtente(utente);
        prenotazione.setPostazione(postazione);

        return prenotazioneDao.save(prenotazione);
    }


    public void elimina(UUID id) {
        if (!prenotazioneDao.existsById(id)) {
            throw new RuntimeException("Prenotazione non trovata con id: " + id);
        }
        prenotazioneDao.deleteById(id);
    }
}
