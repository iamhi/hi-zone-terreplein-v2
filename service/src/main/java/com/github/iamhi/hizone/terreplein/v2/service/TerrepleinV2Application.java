package com.github.iamhi.hizone.terreplein.v2.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.github.iamhi.hizone.terreplein.v2"})
@ConfigurationPropertiesScan("com.github.iamhi.hizone.terreplein.v2.config")
@EnableJpaRepositories("com.github.iamhi.hizone.terreplein.v2.data.*")
@EntityScan("com.github.iamhi.hizone.terreplein.v2.data.*")
@EnableScheduling
public class TerrepleinV2Application {

    public static void main(String[] args) {
        SpringApplication.run(TerrepleinV2Application.class, args);
    }
}
