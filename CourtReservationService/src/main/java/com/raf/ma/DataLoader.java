package com.raf.ma;

import com.raf.ma.model.*;
import com.raf.ma.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    private final TrenerRepository trenerRepository;
    private final SportskaGrupaRepository grupaRepository;
    private final TerenRepository terenRepository;
    private final TerminRepository terminRepository;

    public DataLoader(TrenerRepository trenerRepository,
                      SportskaGrupaRepository grupaRepository,
                      TerenRepository terenRepository,
                      TerminRepository terminRepository) {
        this.trenerRepository = trenerRepository;
        this.grupaRepository = grupaRepository;
        this.terenRepository = terenRepository;
        this.terminRepository = terminRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Trener t1 = new Trener(); t1.setIme("Marko"); t1.setPrezime("Markovic"); t1.setZvanje("Magistar sporta"); t1.setSpecijalnost("Fudbal");
        Trener t2 = new Trener(); t2.setIme("Jovana"); t2.setPrezime("Petrovic"); t2.setZvanje("Dipl. trener"); t2.setSpecijalnost("Kosarka");
        Trener t3 = new Trener(); t3.setIme("Nikola"); t3.setPrezime("Ilic"); t3.setZvanje("Dipl. trener"); t3.setSpecijalnost("Rukomet");
        trenerRepository.saveAll(Arrays.asList(t1, t2, t3));

        SportskaGrupa g1 = new SportskaGrupa(); g1.setOznakaGrupe("Grupa 1"); g1.setTipSporta(TipSporta.FUDBAL); g1.setMaksimalniBrojClanova(15); g1.setTakmicarska(true);
        SportskaGrupa g2 = new SportskaGrupa(); g2.setOznakaGrupe("Grupa 2"); g2.setTipSporta(TipSporta.KOSARKA); g2.setMaksimalniBrojClanova(12); g2.setTakmicarska(false);
        SportskaGrupa g3 = new SportskaGrupa(); g3.setOznakaGrupe("Grupa 3"); g3.setTipSporta(TipSporta.TENIS); g3.setMaksimalniBrojClanova(10); g3.setTakmicarska(true);
        SportskaGrupa g4 = new SportskaGrupa(); g4.setOznakaGrupe("Grupa 4"); g4.setTipSporta(TipSporta.FUDBAL); g4.setMaksimalniBrojClanova(8); g4.setTakmicarska(false);
        SportskaGrupa g5 = new SportskaGrupa(); g5.setOznakaGrupe("Grupa 5"); g5.setTipSporta(TipSporta.KOSARKA); g5.setMaksimalniBrojClanova(14); g5.setTakmicarska(true);
        SportskaGrupa g6 = new SportskaGrupa(); g6.setOznakaGrupe("Grupa 6"); g6.setTipSporta(TipSporta.ODBOJKA); g6.setMaksimalniBrojClanova(16); g6.setTakmicarska(false);

        g1.getTreneri().add(t1);
        t1.getGrupe().add(g1);
        g2.getTreneri().add(t2);
        t2.getGrupe().add(g2);
        g3.getTreneri().add(t3);
        t3.getGrupe().add(g3);
        g4.getTreneri().add(t1);
        t1.getGrupe().add(g4);
        g5.getTreneri().add(t2);
        t2.getGrupe().add(g5);
        g6.getTreneri().add(t3);
        t3.getGrupe().add(g6);

        grupaRepository.saveAll(Arrays.asList(g1,g2,g3,g4,g5,g6));

        Teren tr1 = new Teren(); tr1.setOznaka("Teren A"); tr1.setTipTerena(TipTerena.PARKET); tr1.setKapacitet(20); tr1.setUnutrasnji(true);
        Teren tr2 = new Teren(); tr2.setOznaka("Teren B"); tr2.setTipTerena(TipTerena.TRAVA); tr2.setKapacitet(15); tr2.setUnutrasnji(false);
        terenRepository.saveAll(Arrays.asList(tr1,tr2));

/**
        Termin termin1 = new Termin();
        termin1.setTeren(tr1);
        termin1.setTrener(t1);
        termin1.setSportskaGrupa(g1);
        termin1.setDanUNedelji(DanUNedelji.PONEDELJAK);
        termin1.setVremePocetka(LocalTime.of(8,0));
        termin1.setVremeZavrsetka(LocalTime.of(9,0));
        termin1.setTipTermina(TipTermina.TRENING);

        Termin termin2 = new Termin();
        termin2.setTeren(tr2);
        termin2.setTrener(t2);
        termin2.setSportskaGrupa(g2);
        termin2.setDanUNedelji(DanUNedelji.UTORAK);
        termin2.setVremePocetka(LocalTime.of(9,0));
        termin2.setVremeZavrsetka(LocalTime.of(10,0));
        termin2.setTipTermina(TipTermina.TAKMICENJE);

        Termin termin3 = new Termin();
        termin3.setTeren(tr1);
        termin3.setTrener(t3);
        termin3.setSportskaGrupa(g3);
        termin3.setDanUNedelji(DanUNedelji.SREDA);
        termin3.setVremePocetka(LocalTime.of(10,0));
        termin3.setVremeZavrsetka(LocalTime.of(11,0));
        termin3.setTipTermina(TipTermina.SLOBODNO_VREME);

        Termin termin4 = new Termin();
        termin4.setTeren(tr2);
        termin4.setTrener(t1);
        termin4.setSportskaGrupa(g4);
        termin4.setDanUNedelji(DanUNedelji.CETVRTAK);
        termin4.setVremePocetka(LocalTime.of(11,0));
        termin4.setVremeZavrsetka(LocalTime.of(12,0));
        termin4.setTipTermina(TipTermina.TRENING);

        Termin termin5 = new Termin();
        termin5.setTeren(tr1);
        termin5.setTrener(t2);
        termin5.setSportskaGrupa(g5);
        termin5.setDanUNedelji(DanUNedelji.PETAK);
        termin5.setVremePocetka(LocalTime.of(12,0));
        termin5.setVremeZavrsetka(LocalTime.of(13,0));
        termin5.setTipTermina(TipTermina.TAKMICENJE);

        Termin termin6 = new Termin();
        termin6.setTeren(tr2);
        termin6.setTrener(t3);
        termin6.setSportskaGrupa(g6);
        termin6.setDanUNedelji(DanUNedelji.SUBOTA);
        termin6.setVremePocetka(LocalTime.of(13,0));
        termin6.setVremeZavrsetka(LocalTime.of(14,0));
        termin6.setTipTermina(TipTermina.SLOBODNO_VREME);

        Termin termin7 = new Termin();
        termin7.setTeren(tr1);
        termin7.setTrener(t1);
        termin7.setSportskaGrupa(g1);
        termin7.setDanUNedelji(DanUNedelji.NEDELJA);
        termin7.setVremePocetka(LocalTime.of(14,0));
        termin7.setVremeZavrsetka(LocalTime.of(15,0));
        termin7.setTipTermina(TipTermina.TRENING);

        Termin termin8 = new Termin();
        termin8.setTeren(tr2);
        termin8.setTrener(t2);
        termin8.setSportskaGrupa(g2);
        termin8.setDanUNedelji(DanUNedelji.PONEDELJAK);
        termin8.setVremePocetka(LocalTime.of(15,0));
        termin8.setVremeZavrsetka(LocalTime.of(16,0));
        termin8.setTipTermina(TipTermina.TAKMICENJE);

        Termin termin9 = new Termin();
        termin9.setTeren(tr1);
        termin9.setTrener(t3);
        termin9.setSportskaGrupa(g3);
        termin9.setDanUNedelji(DanUNedelji.UTORAK);
        termin9.setVremePocetka(LocalTime.of(16,0));
        termin9.setVremeZavrsetka(LocalTime.of(17,0));
        termin9.setTipTermina(TipTermina.SLOBODNO_VREME);

        Termin termin10 = new Termin();
        termin10.setTeren(tr2);
        termin10.setTrener(t1);
        termin10.setSportskaGrupa(g4);
        termin10.setDanUNedelji(DanUNedelji.SREDA);
        termin10.setVremePocetka(LocalTime.of(17,0));
        termin10.setVremeZavrsetka(LocalTime.of(18,0));
        termin10.setTipTermina(TipTermina.TRENING);

*/
        Termin termin1 = new Termin();
        termin1.setTeren(tr1);
        termin1.setTrener(t1);
        termin1.setSportskaGrupa(g1);
        termin1.setVremePocetka(LocalDateTime.of(2025, 11, 17, 8, 0));
        termin1.setVremeZavrsetka(LocalDateTime.of(2025, 11, 17, 9, 0));
        termin1.setTipTermina(TipTermina.TRENING);

        Termin termin2 = new Termin();
        termin2.setTeren(tr2);
        termin2.setTrener(t2);
        termin2.setSportskaGrupa(g2);
        termin2.setVremePocetka(LocalDateTime.of(2025, 11, 18, 9, 0));
        termin2.setVremeZavrsetka(LocalDateTime.of(2025, 11, 18, 10, 0));
        termin2.setTipTermina(TipTermina.TAKMICENJE);

        Termin termin3 = new Termin();
        termin3.setTeren(tr1);
        termin3.setTrener(t3);
        termin3.setSportskaGrupa(g3);
        termin3.setVremePocetka(LocalDateTime.of(2025, 11, 19, 10, 0));
        termin3.setVremeZavrsetka(LocalDateTime.of(2025, 11, 19, 11, 0));
        termin3.setTipTermina(TipTermina.SLOBODNO_VREME);

        Termin termin4 = new Termin();
        termin4.setTeren(tr2);
        termin4.setTrener(t1);
        termin4.setSportskaGrupa(g4);
        termin4.setVremePocetka(LocalDateTime.of(2025, 11, 20, 11, 0));
        termin4.setVremeZavrsetka(LocalDateTime.of(2025, 11, 20, 12, 0));
        termin4.setTipTermina(TipTermina.TRENING);

        Termin termin5 = new Termin();
        termin5.setTeren(tr1);
        termin5.setTrener(t2);
        termin5.setSportskaGrupa(g5);
        termin5.setVremePocetka(LocalDateTime.of(2025, 11, 21, 12, 0));
        termin5.setVremeZavrsetka(LocalDateTime.of(2025, 11, 21, 13, 0));
        termin5.setTipTermina(TipTermina.TAKMICENJE);

        Termin termin6 = new Termin();
        termin6.setTeren(tr2);
        termin6.setTrener(t3);
        termin6.setSportskaGrupa(g6);
        termin6.setVremePocetka(LocalDateTime.of(2025, 11, 22, 13, 0));
        termin6.setVremeZavrsetka(LocalDateTime.of(2025, 11, 22, 14, 0));
        termin6.setTipTermina(TipTermina.SLOBODNO_VREME);

        Termin termin7 = new Termin();
        termin7.setTeren(tr1);
        termin7.setTrener(t1);
        termin7.setSportskaGrupa(g1);
        termin7.setVremePocetka(LocalDateTime.of(2025, 11, 23, 14, 0));
        termin7.setVremeZavrsetka(LocalDateTime.of(2025, 11, 23, 15, 0));
        termin7.setTipTermina(TipTermina.TRENING);

        Termin termin8 = new Termin();
        termin8.setTeren(tr2);
        termin8.setTrener(t2);
        termin8.setSportskaGrupa(g2);
        termin8.setVremePocetka(LocalDateTime.of(2025, 11, 17, 15, 0));
        termin8.setVremeZavrsetka(LocalDateTime.of(2025, 11, 17, 16, 0));
        termin8.setTipTermina(TipTermina.TAKMICENJE);

        Termin termin9 = new Termin();
        termin9.setTeren(tr1);
        termin9.setTrener(t3);
        termin9.setSportskaGrupa(g3);
        termin9.setVremePocetka(LocalDateTime.of(2025, 11, 18, 16, 0));
        termin9.setVremeZavrsetka(LocalDateTime.of(2025, 11, 18, 17, 0));
        termin9.setTipTermina(TipTermina.SLOBODNO_VREME);

        Termin termin10 = new Termin();
        termin10.setTeren(tr2);
        termin10.setTrener(t1);
        termin10.setSportskaGrupa(g4);
        termin10.setVremePocetka(LocalDateTime.of(2025, 11, 19, 17, 0));
        termin10.setVremeZavrsetka(LocalDateTime.of(2025, 11, 19, 18, 0));
        termin10.setTipTermina(TipTermina.TRENING);

        terminRepository.saveAll(Arrays.asList(
                termin1, termin2, termin3, termin4, termin5,
                termin6, termin7, termin8, termin9, termin10));

    }
}