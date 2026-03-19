package com.raf.ma.controller;

import com.raf.ma.model.SportskaGrupa;
import com.raf.ma.model.TipSporta;
import com.raf.ma.service.SportskaGrupaService;
import com.raf.ma.service.TerenService;
import com.raf.ma.service.TrenerService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sportskagrupa")
public class SportskaGrupaController {

    private final SportskaGrupaService sportskaGrupaService;

    public SportskaGrupaController(SportskaGrupaService sportskaGrupaService) { this.sportskaGrupaService = sportskaGrupaService;}

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SportskaGrupa createGrupa(@RequestBody SportskaGrupa grupa) {
        return sportskaGrupaService.createGrupa(grupa);
    }

    @GetMapping("/preuzmi")
    public ResponseEntity<List<SportskaGrupa>> getGrupeByTipSporta(@RequestParam String tipSporta) {
        List<SportskaGrupa> grupe = sportskaGrupaService.findByTipSporta(Enum.valueOf(TipSporta.class, tipSporta));
        return ResponseEntity.ok(grupe);
    }

}
