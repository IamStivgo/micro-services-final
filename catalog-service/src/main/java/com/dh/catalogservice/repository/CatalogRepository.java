package com.dh.catalogservice.repository;

import com.dh.catalogservice.model.CatalogWS;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CatalogRepository extends MongoRepository<CatalogWS,String> {
    @Query(value = "{genre: '?0'}")
    CatalogWS findByGenre(String genre);


}
