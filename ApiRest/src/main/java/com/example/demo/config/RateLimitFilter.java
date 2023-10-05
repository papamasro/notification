package com.example.demo.config;


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
        String recipient = request.getHeader("UserId");
        String notificationType = request.getHeader("Notification-Type");
        if (recipient != null && notificationType != null) {
            String bucketKey = recipient + "-" + notificationType;
            Bucket bucket = buckets.computeIfAbsent(bucketKey, key -> {
                Bandwidth limit = switch (notificationType) {
                    case "Status" -> Bandwidth.classic(2, Refill.intervally(2, Duration.ofMinutes(1)));
                    case "News" -> Bandwidth.classic(1, Refill.intervally(1, Duration.ofDays(1)));
                    case "Marketing" -> Bandwidth.classic(3, Refill.intervally(3, Duration.ofHours(1)));
                    default -> Bandwidth.classic(1, Refill.intervally(1, Duration.ofHours(12))); // TODO: ask what happen in this case, without type the default configuration is 1 notification every 12 hs

                };
                return Bucket4j.builder().addLimit(limit).build();
            });
            if (bucket.tryConsume(1)) {
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.getWriter().write("You have exceeded the rate limit for this type of message for this user, try again in a few minutes.");
            }
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write("Missing userId or notification type headers.");
        }
    }


}

