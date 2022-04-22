package com.dh.catalogservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieWS {
    private Integer id;
    private String name;
    private String genre;
    private String urlStream;
}
