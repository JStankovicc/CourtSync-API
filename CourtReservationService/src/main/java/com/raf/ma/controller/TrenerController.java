package com.raf.ma.controller;

import com.raf.ma.model.Trener;
import com.raf.ma.service.TrenerService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trener")
public class TrenerController {

    private final TrenerService trenerService;

    public TrenerController(TrenerService trenerService) {this.trenerService = trenerService;}

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Trener createTrener(@RequestBody Trener trener) {
        return trenerService.createTrener(trener);
    }

    @GetMapping("/preuzmi")
    public ResponseEntity<List<Trener>> getTrenerByImePrezime(@RequestParam String ime,
                                                              @RequestParam String prezime) {
        List<Trener> treneri = trenerService.findByImePrezime(ime, prezime);
        return ResponseEntity.ok(treneri);
    }

}
