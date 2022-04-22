package com.dh.catalogservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChapterWS {
    private String id;
    private String name;
    private Integer number;
    private String urlStream;
}
