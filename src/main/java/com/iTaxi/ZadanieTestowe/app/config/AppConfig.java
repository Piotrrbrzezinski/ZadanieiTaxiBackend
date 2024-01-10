package com.iTaxi.ZadanieTestowe.app.config;

import org.modelmapper.ModelMapper;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("springdoc")
                .packagesToScan("com.iTaxi.ZadanieTestowe.app.controllers") // Zastąp odpowiednią nazwą pakietu
                .build();
    }
}

