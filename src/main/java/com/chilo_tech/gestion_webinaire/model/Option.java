package com.chilo_tech.gestion_webinaire.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "option")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    private Poll poll;
}
