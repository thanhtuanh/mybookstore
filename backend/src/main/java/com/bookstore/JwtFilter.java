package com.bookstore;

import com.bookstore.service.JwtService;
import com.bookstore.user.CustomUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && SecurityContextHolder.getContext().getAuthentication() == null) {
                try {
                    String username = jwtService.extractUsername(jwt);

                    if (StringUtils.hasText(username)) {
                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                        if (jwtService.validateToken(jwt, userDetails)) {
                            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                    userDetails, null, userDetails.getAuthorities());

                            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(authToken);
                        }
                    }
                } catch (ExpiredJwtException e) {
                    logger.warn("Abgelaufenes JWT für IP {}: {}", request.getRemoteAddr(), e.getMessage());

                    if (isApiRequest(request)) {
                        sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED,
                                "token_expired", "Ihr Token ist abgelaufen. Bitte melden Sie sich erneut an.");
                        return;
                    }
                } catch (JwtException e) {
                    logger.warn("Ungültiges JWT für IP {}: {}", request.getRemoteAddr(), e.getMessage());

                    if (isApiRequest(request)) {
                        sendErrorResponse(response, HttpServletResponse.SC_UNAUTHORIZED,
                                "invalid_token", "Ungültiger Authentifizierungstoken.");
                        return;
                    }
                }
            }

            filterChain.doFilter(request, response);

        } catch (Exception e) {
            logger.error("Interner Fehler bei der JWT-Authentifizierung", e);
            sendErrorResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "auth_error", "Fehler bei der Authentifizierung.");
        }
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        return (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer "))
                ? bearerToken.substring(7)
                : null;
    }

    private boolean isApiRequest(HttpServletRequest request) {
        String path = request.getRequestURI();
        String contentType = request.getHeader("Content-Type");
        return path.startsWith("/api/") ||
                (contentType != null && contentType.contains(MediaType.APPLICATION_JSON_VALUE));
    }

    private void sendErrorResponse(HttpServletResponse response, int status, String error, String message)
            throws IOException {
        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String json = String.format("{\"error\":\"%s\",\"message\":\"%s\"}", error, message);
        response.getWriter().write(json);
        response.getWriter().flush(); // Sicherstellen, dass die Antwort gesendet wird
    }
}
