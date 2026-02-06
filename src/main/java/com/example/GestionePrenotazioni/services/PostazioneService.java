package com.example.GestionePrenotazioni.services;

import com.example.GestionePrenotazioni.entities.Postazione;
import com.example.GestionePrenotazioni.entities.TipoPostazione;
import com.example.GestionePrenotazioni.repositories.PostazioneDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostazioneService {

    private final PostazioneDao postazioneDao;

    public PostazioneService(PostazioneDao postazioneDao) {
        this.postazioneDao = postazioneDao;
    }


    public Postazione crea(Postazione postazione) {
        return postazioneDao.save(postazione);
    }


    public Postazione trovaPerId(UUID id) {
        return postazioneDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Postazione non trovata con id: " + id));
    }


    public List<Postazione> trovaPostazioni() {
        return postazioneDao.findAll();
    }


    public List<Postazione> cercaPerTipoECitta(TipoPostazione tipoPostazione, String citta) {
        return postazioneDao.findByTipoPostazioneAndEdificio_Citta(tipoPostazione, citta);
    }


}
