package com.chilo_tech.gestion_webinaire.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "poll")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private Timestamp dateCreation;

    @Column(nullable = false)
    private boolean isMultipleOption = false;

    @ManyToOne
    private Utililsateur utililsateur;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Option> options;

}
