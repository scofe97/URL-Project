package org.example.practice.adapterout.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@ComponentScan(basePackages = "org.example.practice.adapterout")
@EnableJpaRepositories(basePackages = "org.example.practice.adapterout")
@EntityScan(basePackages = "org.example.practice.adapterout")
public class TestJpaConfig {
}
