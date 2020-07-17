package com.algaworks.algamoneyapi.algamoney.api.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("algamoney")
@Data
public class AlgamoneyApiProperty {

    private final Seguranca seguranca = new Seguranca();
    private String originPermitida = "http://localhost:8000";

    @Data
    public static class Seguranca {

        private boolean enableHttps;
    }
}
