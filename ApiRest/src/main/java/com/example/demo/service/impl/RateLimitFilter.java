package com.example.demo.service.impl;


import io.github.bucket4j.Bandwidth;
        import io.github.bucket4j.Bucket;
        import io.github.bucket4j.Bucket4j;
        import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
        import org.springframework.http.HttpStatus;
        import org.springframework.stereotype.Component;
        import org.springframework.web.filter.OncePerRequestFilter;

        import java.io.IOException;
        import java.time.Duration;
        import java.util.Map;
        import java.util.concurrent.ConcurrentHashMap;

@Component
@Order(1)
public class RateLimitFilter extends OncePerRequestFilter {
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String recipient = request.getHeader("Recipient"); // Supongamos que hay un encabezado "Recipient" en la solicitud que identifica al destinatario.
        String notificationType = request.getHeader("Notification-Type"); // Supongamos que hay un encabezado "Notification-Type" en la solicitud que identifica el tipo de notificación.

        if (recipient != null && notificationType != null) {
            String bucketKey = recipient + "-" + notificationType;
            Bucket bucket = buckets.computeIfAbsent(bucketKey, key -> {
                // Configura el límite de velocidad según el tipo de notificación
                Bandwidth limit;
                if ("Status".equals(notificationType)) {
                    limit = Bandwidth.classic(2, Refill.intervally(2, Duration.ofMinutes(1)));
                } else if ("News".equals(notificationType)) {
                    limit = Bandwidth.classic(1, Refill.intervally(1,Duration.ofDays(1)));
                } else if ("Marketing".equals(notificationType)) {
                    limit = Bandwidth.classic(3, Refill.intervally(3, Duration.ofHours(1)));
                } else {
                    // Define límites de velocidad por defecto para otros tipos de notificación
                    limit = Bandwidth.classic(10, Refill.intervally(10, Duration.ofMinutes(1)));
                }

                return Bucket4j.builder().addLimit(limit).build();
            });

            if (bucket.tryConsume(1)) {
                // El usuario está dentro del límite de velocidad, continúa con la solicitud
                filterChain.doFilter(request, response);
            } else {
                // El usuario ha superado el límite de velocidad
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.getWriter().write("Excedido el límite de velocidad para las notificaciones.");
            }
        } else {
            // Faltan encabezados necesarios para la limitación de velocidad
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write("Faltan encabezados de destinatario o tipo de notificación.");
        }
    }


}

