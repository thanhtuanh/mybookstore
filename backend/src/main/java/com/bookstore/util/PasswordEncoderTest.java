package com.bookstore.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j // Aktiviert das Logging für diese Klasse
@Configuration
public class PasswordEncoderTest {

    @Bean
    public CommandLineRunner testPasswordEncoder() {
        return args -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String plainPassword = "admin"; // Klartext-Passwort
            String encodedPassword = encoder.encode(plainPassword); // Passwort verschlüsseln

            log.info("====== PASSWORT DEBUG ======");
            log.info("Klartext: {}", plainPassword); // Logge das Klartext-Passwort
            log.debug("Encoded: {}", encodedPassword); // Logge das verschlüsselte Passwort (nur im Debug-Level)

            // Der gespeicherte Hash für das Passwort 'admin'
            String storedHash = "$2a$10$HyuZ/MLnsMQ29.PZgp46mOBI2DaZ8nrfP2msUxDwkHfZecFTNOcyK"; // Festes Passwort für
                                                                                                // 'admin'

            // Überprüfen, ob das gespeicherte Passwort mit dem Klartext-Passwort
            // übereinstimmt
            boolean isMatch = encoder.matches(plainPassword, storedHash); // Überprüfen der Übereinstimmung

            log.info("Passwort stimmt überein: {}", isMatch); // Logge das Ergebnis des Vergleichs
            log.info("============================");
        };
    }
}
