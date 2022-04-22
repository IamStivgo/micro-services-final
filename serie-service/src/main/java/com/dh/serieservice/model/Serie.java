package com.dh.serieservice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter @Setter
@Document("series")
public class Serie {
    @Id
    private String id;
    private String name;
    private String genre;
    private List<Season> seasons;

    public Serie(String id, String name, String genre, List<Season> seasons) {
        super();
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.seasons = seasons;
    }
}
