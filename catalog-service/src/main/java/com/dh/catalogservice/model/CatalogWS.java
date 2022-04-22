package com.dh.catalogservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter @Setter
@JsonIgnoreProperties("id")
@Document("catalog")
public class CatalogWS {
	@Id
	private String id;
	private String genre;
	private List<MovieWS> movies;
	private List<SerieWS> series;

	public CatalogWS(String genre) {
		this.genre = genre;
	}
}
