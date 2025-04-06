package com.chilo_tech.gestion_webinaire.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


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
    private Date dateVote;

    @ManyToOne
    private Utililsateur utililsateur;

    @ManyToOne
    private Poll poll;

    @ManyToOne
    private Option option;
}
