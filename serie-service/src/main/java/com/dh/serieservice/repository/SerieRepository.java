package com.dh.serieservice.repository;

import com.dh.serieservice.model.Serie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SerieRepository extends MongoRepository<Serie,String> {
    @Query(value = "{genre: '?0'}")
    List<Serie> findAllByGenre(String genre);
}
