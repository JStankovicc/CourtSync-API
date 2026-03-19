package com.raf.ma.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Trener {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ime;
    private String prezime;
    private String zvanje;
    private String specijalnost;

    @ManyToMany(mappedBy = "treneri")
    @JsonIgnore
    private Set<SportskaGrupa> grupe = new HashSet<>();


    public Trener(Long trenerId) {
        this.id = trenerId;
    }

    public Trener() {

    }
}
