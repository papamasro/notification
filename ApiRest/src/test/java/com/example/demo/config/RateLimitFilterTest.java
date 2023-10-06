package com.example.demo.config;


import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.io.PrintWriter;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

class RateLimitFilterTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    private RateLimitFilter rateLimitFilter;

    private Map<String, Bucket> buckets;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        buckets = new HashMap<>();
        rateLimitFilter = new RateLimitFilter();
    }

    @Test
    void testDoFilterInternal_WithValidHeadersAndAvailableTokens_ShouldPass() throws Exception {
        when(request.getHeader("UserId")).thenReturn("user123");
        when(request.getHeader("Notification-Type")).thenReturn("Status");
        Bucket bucket = Bucket4j.builder().addLimit(Bandwidth.classic(10, Refill.intervally(10, Duration.ofMinutes(1)))).build();
        buckets.put("user123-Status", bucket);

        rateLimitFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
    }

    /*
    @Test
    void testDoFilterInternal_WithValidHeadersAndExceededRateLimit_ShouldReturnTooManyRequests() throws Exception {
        when(request.getHeader("UserId")).thenReturn("user123");
        when(request.getHeader("Notification-Type")).thenReturn("Status");
        Bucket bucket = Bucket4j.builder().addLimit(Bandwidth.classic(1, Refill.intervally(1, Duration.ofMinutes(1)))).build();
        buckets.put("user123-Status", bucket);
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));

        rateLimitFilter.doFilterInternal(request, response, filterChain);


        verify(response, times(1)).setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
    }

     */

    @Test
    void testDoFilterInternal_WithMissingHeaders_ShouldReturnBadRequest() throws Exception {
        when(request.getHeader("UserId")).thenReturn(null);
        when(request.getHeader("Notification-Type")).thenReturn(null);
        when(response.getWriter()).thenReturn(new PrintWriter(System.out));

        rateLimitFilter.doFilterInternal(request, response, filterChain);

        verify(response, times(1)).setStatus(HttpStatus.BAD_REQUEST.value());
    }
}