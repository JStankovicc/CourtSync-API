package com.raf.ma.service;

import com.raf.ma.model.Trener;
import com.raf.ma.repositories.TrenerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrenerService {

    private final TrenerRepository trenerRepository;

    public TrenerService(TrenerRepository trenerRepository) {this.trenerRepository = trenerRepository;}

    public Trener createTrener(Trener trener) {
        return trenerRepository.save(trener);
    }

    public List<Trener> findByImePrezime(String ime, String prezime) {
        return trenerRepository.findByImeAndPrezime(ime, prezime);
    }
}
