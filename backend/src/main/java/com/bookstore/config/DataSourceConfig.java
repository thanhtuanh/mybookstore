package com.bookstore.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class DataSourceConfig {

    private final DataSource dataSource;

    public DataSourceConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public CommandLineRunner checkDatabaseConnection() {
        return args -> {
            try {
                log.info("====== 📦 DATENBANK DEBUG ======");
                String dbUrl = dataSource.getConnection().getMetaData().getURL();
                log.info("🔗 Datenbank-URL: {}", dbUrl);
                log.info("✅ Datenbank-Verbindung erfolgreich!");

                JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
                Integer userCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
                log.info("👥 Anzahl der Benutzer in der Datenbank: {}", userCount);
                log.info("================================");

            } catch (Exception e) {
                log.error("❌ Fehler bei der Datenbankverbindung: {}", e.getMessage(), e);
            }
        };
    }
}
