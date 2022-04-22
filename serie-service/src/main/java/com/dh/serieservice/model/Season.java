package com.dh.serieservice.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter @Setter
public class Season {
    private String id;
    private Integer seasonNumber;
    private List<Chapter> chapters;

    public Season(String id, Integer seasonNumber, List<Chapter> chapters) {
        this.id = new ObjectId().toString();
        this.seasonNumber = seasonNumber;
        this.chapters = chapters;
    }
}
