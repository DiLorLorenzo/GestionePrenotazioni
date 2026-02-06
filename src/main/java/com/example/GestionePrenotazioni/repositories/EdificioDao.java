package com.example.GestionePrenotazioni.repositories;

import com.example.GestionePrenotazioni.entities.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EdificioDao extends JpaRepository<Edificio, UUID> {

}