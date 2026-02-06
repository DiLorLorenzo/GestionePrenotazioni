package com.example.GestionePrenotazioni.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "postazione")
public class Postazione {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "codice", nullable = false, unique = true)
    private String codicePostazione;

    @Column(nullable = false)
    private String descrizione;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoPostazione tipoPostazione;


    @Column(nullable = false)
    private int capienzaMax;

    @ManyToOne(optional = false)
    @JoinColumn(name = "edificio_id", nullable = false)
    private Edificio edificio;
}
