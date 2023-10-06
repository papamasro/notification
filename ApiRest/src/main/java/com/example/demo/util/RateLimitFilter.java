package com.example.demo.util;


import com.example.demo.service.impl.NotificationService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class RateLimitFilter extends OncePerRequestFilter { //all requests go through this filter, in case you have a problem with the headers look here
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(RateLimitFilter.class);

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String recipient = request.getHeader("UserId");
        String notificationType = request.getHeader("Notification-Type");
        if (recipient != null && notificationType != null) {
            String bucketKey = recipient + "-" + notificationType;
            Bucket bucket = buckets.computeIfAbsent(bucketKey, key -> {
                Bandwidth limit = switch (notificationType) {
                    case "Status" -> Bandwidth.classic(2, Refill.intervally(2, Duration.ofMinutes(1)));
                    case "News" -> Bandwidth.classic(1, Refill.intervally(1, Duration.ofDays(1)));
                    case "Marketing" -> Bandwidth.classic(3, Refill.intervally(3, Duration.ofHours(1)));
                    default -> Bandwidth.classic(1, Refill.intervally(1, Duration.ofHours(12)));
                    // TODO: ask what happen in this case, without type the default configuration is 1 notification every 12 hs

                };
                return Bucket4j.builder().addLimit(limit).build();
            });
            if (bucket.tryConsume(1)) {
                logger.info("token consumed, sending message to user " + recipient + " and type " + notificationType);

                filterChain.doFilter(request, response);
            } else {
                logger.info("message blocked for rate limiter to user:" + recipient + " and type " + notificationType);

                response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                response.getWriter().write("You have exceeded the rate limit for this type of message for this user, try again in a few minutes.");
            }
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write("Missing userId or notification type headers.");
        }
    }


}

