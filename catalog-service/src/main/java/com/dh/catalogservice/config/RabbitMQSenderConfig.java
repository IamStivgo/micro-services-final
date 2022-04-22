package com.dh.catalogservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQSenderConfig {

    @Value("${queue.movie.name}")
    private String movieQueue;

    @Value("${queue.serie.name}")
    private String serieQueue;

    @Bean
    public Queue movieQ() {
        return new Queue(this.movieQueue, true);
    }

    @Bean
    public Queue serieQ() {
        return new Queue(this.serieQueue, true);
    }

}
