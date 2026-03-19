package com.raf.ma.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Termin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teren_id")
    private Teren teren;

    @ManyToOne
    @JoinColumn(name = "trener_id")
    private Trener trener;

    @ManyToOne
    @JoinColumn(name = "sportska_grupa_id")
    private SportskaGrupa sportskaGrupa;


//    private LocalTime vremePocetka;
//    private LocalTime vremeZavrsetka;

    private LocalDateTime vremePocetka;
    private LocalDateTime vremeZavrsetka;

    @Enumerated(EnumType.STRING)
    private DanUNedelji danUNedelji;

    @Enumerated(EnumType.STRING)
    private TipTermina tipTermina;


}
