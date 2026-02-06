package com.example.GestionePrenotazioni.repositories;

import com.example.GestionePrenotazioni.entities.Postazione;
import com.example.GestionePrenotazioni.entities.TipoPostazione;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface PostazioneDao extends JpaRepository<Postazione, UUID> {


    List<Postazione> findByTipoPostazioneAndEdificio_Citta(TipoPostazione tipo, String citta);

}
