package com.raf.ma.repositories;

import com.raf.ma.model.SportskaGrupa;
import com.raf.ma.model.TipSporta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SportskaGrupaRepository extends CrudRepository<SportskaGrupa, Long> {
    List<SportskaGrupa> findByTipSporta(TipSporta tipSporta);
}
