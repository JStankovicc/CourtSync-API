package com.raf.ma.service;

import com.raf.ma.model.SportskaGrupa;
import com.raf.ma.model.TipSporta;
import com.raf.ma.repositories.SportskaGrupaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportskaGrupaService {

    private final SportskaGrupaRepository sportskaGrupaRepository;

    public SportskaGrupaService(SportskaGrupaRepository grupaRepository) {this.sportskaGrupaRepository = grupaRepository;}

    public SportskaGrupa createGrupa(SportskaGrupa grupa) {
        return sportskaGrupaRepository.save(grupa);
    }

    public List<SportskaGrupa> findByTipSporta(TipSporta tipSporta) {
        return sportskaGrupaRepository.findByTipSporta(tipSporta);
    }
}
