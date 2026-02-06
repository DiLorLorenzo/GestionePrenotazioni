package com.example.GestionePrenotazioni;

import com.example.GestionePrenotazioni.entities.*;
import com.example.GestionePrenotazioni.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class GestionePrenotazioniRunner implements CommandLineRunner {

    private final EdificioService edificioService;
    private final PostazioneService postazioneService;
    private final UtenteService utenteService;
    private final PrenotazioneService prenotazioneService;

    public GestionePrenotazioniRunner(
            EdificioService edificioService,
            PostazioneService postazioneService,
            UtenteService utenteService,
            PrenotazioneService prenotazioneService
    ) {
        this.edificioService = edificioService;
        this.postazioneService = postazioneService;
        this.utenteService = utenteService;
        this.prenotazioneService = prenotazioneService;
    }

    @Override
    public void run(String... args) {


        Edificio e1 = new Edificio();
        e1.setNome("Ufficio Milano");
        e1.setIndirizzo("Via A.Costa 10");
        e1.setCitta("Milano");
        e1 = edificioService.creaEdificio(e1);

        Edificio e2 = new Edificio();
        e2.setNome("Ufficio Torino");
        e2.setIndirizzo("Corso Francia 1");
        e2.setCitta("Torino");
        e2 = edificioService.creaEdificio(e2);


        Postazione p1 = new Postazione();
        p1.setCodicePostazione("MI-PRIV-01");
        p1.setDescrizione("Ufficio privato");
        p1.setTipoPostazione(TipoPostazione.PRIVATO);
        p1.setCapienzaMax(3);
        p1.setEdificio(e1);
        p1 = postazioneService.crea(p1);

        Postazione p2 = new Postazione();
        p2.setCodicePostazione("MI-OPEN-01");
        p2.setDescrizione("Open space");
        p2.setTipoPostazione(TipoPostazione.OPENSPACE);
        p2.setCapienzaMax(6);
        p2.setEdificio(e1);
        p2 = postazioneService.crea(p2);

        Postazione p3 = new Postazione();
        p3.setCodicePostazione("TO-SALA-01");
        p3.setDescrizione("Sala riunioni");
        p3.setTipoPostazione(TipoPostazione.SALA_RIUNIONI);
        p3.setCapienzaMax(10);
        p3.setEdificio(e2);
        p3 = postazioneService.crea(p3);


        Utente u1 = new Utente();
        u1.setUsername("BreeVanDerCamp");
        u1.setNomeCompleto("Bree VanDerCamp");
        u1.setEmail("Breehw@email.com");
        u1 = utenteService.crea(u1);

        Utente u2 = new Utente();
        u2.setUsername("ldl");
        u2.setNomeCompleto("Lorenzo Di Lorenzo");
        u2.setEmail("ldl97@email.com");
        u2 = utenteService.crea(u2);


        LocalDate data = LocalDate.now().plusDays(1);

        System.out.println("\n=== Prenotazione valida ===");
        prenotazioneService.prenota(u1.getUsername(), p1.getId(), data);

        System.out.println("\n=== Utente già prenotato in questa data ===");
        try {
            prenotazioneService.prenota(u1.getUsername(), p2.getId(), data);
        } catch (RuntimeException ex) {
            System.out.println("ATTESO: " + ex.getMessage());
        }

        System.out.println("\n=== Postazione già occupata in questa data ===");
        try {
            prenotazioneService.prenota(u2.getUsername(), p1.getId(), data);
        } catch (RuntimeException ex) {
            System.out.println("ATTESO: " + ex.getMessage());
        }


    }
}
