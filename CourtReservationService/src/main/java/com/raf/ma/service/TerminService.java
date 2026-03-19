package com.raf.ma.service;


import com.raf.ma.model.*;
import com.raf.ma.repositories.SportskaGrupaRepository;
import com.raf.ma.repositories.TerenRepository;
import com.raf.ma.repositories.TerminRepository;
import com.raf.ma.repositories.TrenerRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TerminService {

    private final TerminRepository terminRepository;
    private final TerenRepository terenRepository;
    private final TrenerRepository trenerRepository;
    private final SportskaGrupaRepository sportskaGrupaRepository;

    public TerminService(TerminRepository terminRepository,
                         TerenRepository terenRepository,
                         TrenerRepository trenerRepository,
                         SportskaGrupaRepository sportskaGrupaRepository) {
        this.terminRepository = terminRepository;
        this.terenRepository = terenRepository;
        this.trenerRepository = trenerRepository;
        this.sportskaGrupaRepository = sportskaGrupaRepository;
    }

    public List<Termin> findByTeren(Teren teren) {
        return terminRepository.findByTeren(teren);
    }

    public List<Termin> findByTrener(Trener trener) {
        return terminRepository.findByTrener(trener);
    }

    public List<Termin> findBySportskaGrupa(SportskaGrupa grupa) {
        return terminRepository.findBySportskaGrupa(grupa);
    }

    public Termin dodajTermin(Long terenId, Long trenerId, Long grupaId, Termin termin) {

        Teren teren = terenRepository.findById(terenId)
                .orElseThrow(() -> new RuntimeException("Teren ne postoji"));
        Trener trener = trenerRepository.findById(trenerId)
                .orElseThrow(() -> new RuntimeException("Trener ne postoji"));
        SportskaGrupa grupa = sportskaGrupaRepository.findById(grupaId)
                .orElseThrow(() -> new RuntimeException("Sportska grupa ne postoji"));

        if (termin.getVremePocetka().isAfter(termin.getVremeZavrsetka())) {
            throw new RuntimeException("Vreme nije validno");
        }

        if (!grupa.getTreneri().contains(trener)) {
            throw new RuntimeException("Trener nije povezan sa ovom grupom");
        }

        List<Termin> terminiTeren = terminRepository
                .findByTerenAndDanUNedelji(teren, termin.getDanUNedelji());

        boolean terenZauzet = terminiTeren.stream().anyMatch(t ->
                termin.getVremePocetka().isBefore(t.getVremeZavrsetka()) &&
                        termin.getVremeZavrsetka().isAfter(t.getVremePocetka())
        );

        if (terenZauzet) {
            throw new RuntimeException("Teren je zauzet");
        }

        List<Termin> terminiTrener = terminRepository
                .findByTrenerAndDanUNedelji(trener, termin.getDanUNedelji());

        boolean trenerZauzet = terminiTrener.stream().anyMatch(t ->
                termin.getVremePocetka().isBefore(t.getVremeZavrsetka()) &&
                        termin.getVremeZavrsetka().isAfter(t.getVremePocetka())
        );

        if (trenerZauzet) {
            throw new RuntimeException("Trener je zauzet");
        }

        termin.setTeren(teren);
        termin.setTrener(trener);
        termin.setSportskaGrupa(grupa);

        return terminRepository.save(termin);
    }



    public void obrisiTermin(LocalTime pocetak, Long terenId, DanUNedelji danUNedelji) {
        Teren teren = terenRepository.findById(terenId)
                .orElseThrow(() -> new RuntimeException("Teren ne postoji"));

        Optional<Termin> termin = terminRepository.findByTerenAndVremePocetkaAndDanUNedelji(
                teren, pocetak, danUNedelji
        );

        if (termin.isPresent()) {
            terminRepository.delete(termin.get());
        } else {
            throw new RuntimeException("Termin nije pronađen.");
        }
    }
/**
    public void obrisiTermin(LocalDateTime pocetak, Long terenId) {
        Teren teren = terenRepository.findById(terenId)
                .orElseThrow(() -> new RuntimeException("Teren ne postoji"));

        Optional<Termin> termin = terminRepository.findByTerenAndVremePocetka(
                teren, pocetak
        );

        if (termin.isPresent()) {
            terminRepository.delete(termin.get());
        } else {
            throw new RuntimeException("Termin nije pronađen.");
        }
    }
*/

    public List<Termin> findAll() {
        return (List<Termin>) terminRepository.findAll();
    }

    public List<Termin> findByTeren(Long terenId, boolean ascending) {
        Teren teren = terenRepository.findById(terenId)
                .orElseThrow(() -> new RuntimeException("Teren ne postoji"));
        Sort sort = Sort.by(ascending ? Sort.Direction.ASC : Sort.Direction.DESC, "vremePocetka");
        return terminRepository.findByTeren(teren, sort);
    }

    public List<Termin> findByDanIVreme(DanUNedelji danUNedelji, LocalTime vremePocetka, boolean ascending) {
        Sort sort = Sort.by(ascending ? Sort.Direction.ASC : Sort.Direction.DESC, "vremePocetka");
        List<Termin> termini = terminRepository.findByDanUNedelji(danUNedelji, sort);

        if (vremePocetka != null) {
            termini = termini.stream()
                    .filter(t -> t.getVremePocetka().equals(vremePocetka))
                    .collect(Collectors.toList());
        }

        return termini;
    }


    public List<Termin> findByTipTermina(TipTermina tipTermina) {
        return terminRepository.findByTipTermina(tipTermina);
    }

    public List<Termin> findByDanAndTeren(DanUNedelji dan, Long terenId) {
        return terminRepository.findByDanUNedeljiAndTerenId(dan, terenId);
    }

    public List<Termin> findByGrupa(Long grupaId) {
        return terminRepository.findBySportskaGrupaId(grupaId);
    }

    public List<Termin> findByTrener(Long trenerId) {
        return terminRepository.findByTrenerId(trenerId);
    }

    public List<Termin> findByTipGrupe(boolean takmicarska) {
        return terminRepository.findBySportskaGrupa_Takmicarska(takmicarska);
    }


}
