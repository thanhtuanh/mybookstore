package com.bookstore.controller;

import com.bookstore.model.LoginRequest;
import com.bookstore.model.User;
import com.bookstore.repository.UserRepository;
import com.bookstore.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            log.info("🔐 Login-Versuch für Benutzer: {}", loginRequest.getUsername());

            Optional<User> userOpt = userRepository.findByUsername(loginRequest.getUsername());

            if (userOpt.isEmpty()) {
                log.warn("❌ Benutzer nicht gefunden: {}", loginRequest.getUsername());
                throw new BadCredentialsException("Benutzer nicht gefunden");
            }

            User user = userOpt.get();
            log.debug("✅ Benutzer gefunden: {}", user.getUsername());
            log.debug("🔒 Gespeichertes Passwort: {}", user.getPassword());
            log.debug("🔑 Eingegebenes Passwort: {}", loginRequest.getPassword());

            boolean passwordMatches = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
            log.debug("🔍 Passwort stimmt überein: {}", passwordMatches);

            if (!passwordMatches) {
                log.warn("❌ Passwort für Benutzer {} ist falsch", user.getUsername());
                throw new BadCredentialsException("Passwort stimmt nicht überein");
            }

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtService.generateToken(userDetails.getUsername());

            log.info("✅ Authentifizierung erfolgreich für Benutzer: {}", userDetails.getUsername());

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("username", userDetails.getUsername());

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            log.error("❌ Authentifizierung fehlgeschlagen: {}", e.getMessage(), e);
            return ResponseEntity.status(401).body(Map.of(
                    "status", 401,
                    "message", "Ungültiger Benutzername oder Passwort",
                    "error", e.getMessage(),
                    "timestamp", LocalDateTime.now().toString()));
        } catch (Exception e) {
            log.error("💥 Unerwarteter Fehler während der Authentifizierung: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body(Map.of(
                    "status", 500,
                    "message", "Ein Fehler ist bei der Authentifizierung aufgetreten: " + e.getMessage(),
                    "timestamp", LocalDateTime.now().toString()));
        }
    }

    // ✅ Neue Methode für GET /api/auth/login (hilfreich für Swagger oder
    // Direktaufruf im Browser)
    @GetMapping("/login")
    public ResponseEntity<String> loginInfo() {
        return ResponseEntity.ok("✅ Bitte sende einen POST-Request mit Benutzername und Passwort an /api/auth/login");
    }
}
