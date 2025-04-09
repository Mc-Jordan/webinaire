package com.chilo_tech.gestion_webinaire.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "option")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Data
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    private Poll poll;
}
