package com.dh.catalogservice.service.queue;

import com.dh.catalogservice.model.MovieWS;
import com.dh.catalogservice.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MovieListener {
    private final CatalogService service;

    @RabbitListener(queues = { "${queue.movie.name}" })
    public void receive(@Payload MovieWS movie){
        service.saveMovieCatalog(movie);
    }

}
