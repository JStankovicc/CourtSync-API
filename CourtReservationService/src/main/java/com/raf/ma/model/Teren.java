package com.raf.ma.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Teren {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String oznaka;

    @Enumerated(EnumType.STRING)
    private TipTerena tipTerena;

    private int kapacitet;
    private boolean unutrasnji;

    public Teren(Long terenId) {
        this.id = terenId;
    }

    public Teren() {

    }
}
