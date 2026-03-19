package com.raf.ma.repositories;

import com.raf.ma.model.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TerminRepository extends CrudRepository<Termin, Long> {
    List<Termin> findByTeren(Teren teren);
    List<Termin> findByTrener(Trener trener);
    List<Termin> findBySportskaGrupa(SportskaGrupa grupa);
    Optional<Termin> findByTerenAndVremePocetkaAndDanUNedelji(Teren teren, LocalTime vremePocetka, DanUNedelji danUNedelji);

    List<Termin> findByTeren(Teren teren, Sort sort);
    List<Termin> findByDanUNedelji(DanUNedelji danUNedelji, Sort sort);
    List<Termin> findByTipTermina(TipTermina tipTermina);

    List<Termin> findByDanUNedeljiAndTerenId(DanUNedelji dan, Long terenId);

    List<Termin> findBySportskaGrupaId(Long sportskaGrupaId);

    List<Termin> findByTrenerId(Long trenerId);

    List<Termin> findBySportskaGrupa_Takmicarska(boolean takmicarska);

    List<Termin> findByTerenAndDanUNedelji(Teren teren, DanUNedelji dan);
    List<Termin> findByTrenerAndDanUNedelji(Trener trener, DanUNedelji dan);

    Optional<Termin> findByTerenAndVremePocetka(Teren teren, LocalDateTime vremePocetka);


}
