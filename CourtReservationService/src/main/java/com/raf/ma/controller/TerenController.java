package com.raf.ma.controller;

import com.raf.ma.model.Teren;
import com.raf.ma.service.TerenService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teren")
public class TerenController {

    private final TerenService terenService;

    public TerenController(TerenService terenService) {this.terenService = terenService;}

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Teren createTeren(@RequestBody Teren teren) {
        return terenService.createTeren(teren);
    }

    @GetMapping("/preuzmi")
    public ResponseEntity<List<Teren>> getTerenByOznaka(@RequestParam String oznaka) {
        List<Teren> terenList = terenService.findByOznaka(oznaka);
        return ResponseEntity.ok(terenList);
    }

}
