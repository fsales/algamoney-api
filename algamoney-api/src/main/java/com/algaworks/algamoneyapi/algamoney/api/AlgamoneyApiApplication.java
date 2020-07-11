package com.algaworks.algamoneyapi.algamoney.api;

import com.algaworks.algamoneyapi.algamoney.api.config.property.AlgamoneyApiProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AlgamoneyApiProperty.class)
public class AlgamoneyApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlgamoneyApiApplication.class, args);
    }

}
