package com.raf.ma.controller;

import com.raf.ma.model.*;
import com.raf.ma.service.TerminService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/termin")
public class TerminController {

    private final TerminService terminService;

    public TerminController(TerminService terminService) {
        this.terminService = terminService;
    }

    @GetMapping("/preuzmi")
    public ResponseEntity<List<Termin>> getTermini(@RequestParam(required = false) Long terenId,
                                                   @RequestParam(required = false) Long trenerId,
                                                   @RequestParam(required = false) Long grupaId) {
        if (terenId != null) {
            return ResponseEntity.ok(terminService.findByTeren(new Teren(terenId)));
        }
        if (trenerId != null) {
            return ResponseEntity.ok(terminService.findByTrener(new Trener(trenerId)));
        }
        if (grupaId != null) {
            return ResponseEntity.ok(terminService.findBySportskaGrupa(new SportskaGrupa(grupaId)));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping(path = "/dodaj", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> dodajTermin(@RequestParam Long terenId,
                                         @RequestParam Long trenerId,
                                         @RequestParam Long grupaId,
                                         @RequestBody Termin termin) {
        try {
            Termin saved = terminService.dodajTermin(terenId, trenerId, grupaId, termin);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
/**
    @DeleteMapping("/obrisi")
    public ResponseEntity<?> obrisiTermin(@RequestParam String vremePocetka,
                                          @RequestParam Long terenId,
                                          @RequestParam DanUNedelji danUNedelji) {
        try {
            LocalTime pocetak = LocalTime.parse(vremePocetka);
            terminService.obrisiTermin(pocetak, terenId, danUNedelji);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/obrisi")
    public ResponseEntity<?> obrisiTermin(@RequestParam String vremePocetka,
                                          @RequestParam Long terenId) {
        try {
            LocalDateTime pocetak = LocalDateTime.parse(vremePocetka);
            terminService.obrisiTermin(pocetak, terenId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


*/
    @GetMapping("/svi")
    public List<Termin> getSviTermini() {
        return terminService.findAll();
    }

    @GetMapping("/po-terenu")
    public List<Termin> getTerminiPoTerenu(@RequestParam Long terenId,
                                           @RequestParam(defaultValue = "true") boolean ascending) {
        return terminService.findByTeren(terenId, ascending);
    }

    @GetMapping("/po-danu")
    public List<Termin> getTerminiPoDanu(
            @RequestParam DanUNedelji danUNedelji,
            @RequestParam(required = false) String vremePocetka,
            @RequestParam(defaultValue = "true") boolean ascending
    ) {
        LocalTime pocetak = null;
        if (vremePocetka != null) {
            pocetak = LocalTime.parse(vremePocetka);
        }
        return terminService.findByDanIVreme(danUNedelji, pocetak, ascending);
    }


    @GetMapping("/po-tipu")
    public List<Termin> getTerminiPoTipu(@RequestParam TipTermina tipTermina) {
        return terminService.findByTipTermina(tipTermina);
    }

    @GetMapping("/dan-i-teren")
    public List<Termin> getTerminiPoDanuITerenu(@RequestParam DanUNedelji dan,
                                                @RequestParam Long terenId) {
        return terminService.findByDanAndTeren(dan, terenId);
    }

    @GetMapping("/grupa")
    public List<Termin> getTerminiPoGrupi(@RequestParam Long grupaId) {
        return terminService.findByGrupa(grupaId);
    }

    @GetMapping("/trener")
    public List<Termin> getTerminiPoTreneru(@RequestParam Long trenerId) {
        return terminService.findByTrener(trenerId);
    }

    @GetMapping("/tip-grupe")
    public List<Termin> getTerminiPoTipuGrupe(@RequestParam boolean takmicarska) {
        return terminService.findByTipGrupe(takmicarska);
    }

    @GetMapping("/tip-termina")
    public List<Termin> getTerminiPoTipuTermina(@RequestParam TipTermina tip) {
        return terminService.findByTipTermina(tip);
    }

}
