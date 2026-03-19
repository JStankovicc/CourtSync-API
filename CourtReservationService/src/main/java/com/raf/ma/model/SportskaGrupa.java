package com.raf.ma.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class SportskaGrupa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String oznakaGrupe;

    @Enumerated(EnumType.STRING)
    private TipSporta tipSporta;

    private int maksimalniBrojClanova;

    private boolean takmicarska;

    @ManyToMany
    @JoinTable(
            name = "grupa_trener",
            joinColumns = @JoinColumn(name = "grupa_id"),
            inverseJoinColumns = @JoinColumn(name = "trener_id")
    )
    private Set<Trener> treneri = new HashSet<>();


    public SportskaGrupa(Long grupaId) {
        this.id = grupaId;
    }

    public SportskaGrupa() {

    }
}
