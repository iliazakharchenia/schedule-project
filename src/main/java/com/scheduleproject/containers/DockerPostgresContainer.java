package com.scheduleproject.containers;

import lombok.Getter;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import javax.annotation.PreDestroy;

@Getter
public class DockerPostgresContainer {
    private final PostgreSQLContainer<?> postgreSQLContainer;
    public DockerPostgresContainer() {
        postgreSQLContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:14.3"))
                .withDatabaseName("university_db")
                .withUsername("postgres")
                .withPassword("password")
                .withExposedPorts(5432)
        ;
    }

    public void start() {
        postgreSQLContainer.start();
        System.setProperty("spring.datasource.url", postgreSQLContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgreSQLContainer.getUsername());
        System.setProperty("spring.datasource.password", postgreSQLContainer.getPassword());
        System.setProperty("spring.flyway.url", postgreSQLContainer.getJdbcUrl());
        System.setProperty("spring.flyway.username", postgreSQLContainer.getUsername());
        System.setProperty("spring.flyway.password", postgreSQLContainer.getPassword());
    }

    @PreDestroy
    public void stop() {
        postgreSQLContainer.stop();
    }
}
