package com.alnura.greco.backend.app;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@Configuration
@EntityScan("com.alnura.greco.backend.entities")
@EnableJpaRepositories("com.alnura.greco.backend.repository")
public class DatabaseConfig {

}
