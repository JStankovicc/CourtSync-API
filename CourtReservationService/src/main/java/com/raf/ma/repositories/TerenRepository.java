package com.raf.ma.repositories;

import com.raf.ma.model.Teren;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerenRepository extends CrudRepository<Teren,Long> {
    List<Teren> findByOznaka(String oznaka);

}
