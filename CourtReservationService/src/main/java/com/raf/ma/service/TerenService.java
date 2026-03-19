package com.raf.ma.service;

import com.raf.ma.model.Teren;
import com.raf.ma.repositories.TerenRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerenService {

    private final TerenRepository terenRepository;

    public TerenService(TerenRepository terenRepository) {this.terenRepository = terenRepository;}

    public Teren createTeren(Teren teren) {
        return terenRepository.save(teren);
    }

    public List<Teren> findByOznaka(String oznaka) {
        return terenRepository.findByOznaka(oznaka);
    }
}
