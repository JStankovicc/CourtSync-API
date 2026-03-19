package com.raf.ma.repositories;

import com.raf.ma.model.Trener;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrenerRepository extends CrudRepository<Trener, Long> {
    List<Trener> findByImeAndPrezime(String ime, String prezime);

}
