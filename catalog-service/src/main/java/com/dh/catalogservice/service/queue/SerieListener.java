package com.dh.catalogservice.service.queue;

import com.dh.catalogservice.model.SerieWS;
import com.dh.catalogservice.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SerieListener {
    private final CatalogService service;

    @RabbitListener(queues = { "${queue.serie.name}" })
    public void receive(@Payload SerieWS serie){
        service.saveSerieCatalog(serie);
    }
}
