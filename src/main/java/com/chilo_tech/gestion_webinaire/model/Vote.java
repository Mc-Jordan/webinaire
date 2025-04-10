package com.chilo_tech.gestion_webinaire.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@Entity
@Table(name = "vote")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Timestamp dateVote;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToOne
    private Poll poll;

    @ManyToOne
    private Option option;
}
