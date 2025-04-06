package com.chilo_tech.gestion_webinaire.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Entity
@Table(name = "utilisateur")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Utililsateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String nomUtilisateur;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String motDePasse;
    @Column(nullable = false)
    private Timestamp dateCreation;
    @Column(nullable = false)
    private String role = "standard";


}
