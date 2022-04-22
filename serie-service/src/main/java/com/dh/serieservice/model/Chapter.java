package com.dh.serieservice.model;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter @Setter
public class Chapter {
    @Id
    private String id;
    private String name;
    private Integer number;
    private String urlStream;

    public Chapter(String id, String name, Integer number, String urlStream) {
        super();
        this.id = new ObjectId().toString();
        this.name = name;
        this.number = number;
        this.urlStream = urlStream;
    }
}
