 package com.sistema.pizzeria.elpirata.api.core.config.security;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

	private final ConcurrentHashMap<String, Bucket> buckets = new ConcurrentHashMap<>();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String clientIp = getClientIp(request);

		Bucket bucket = buckets.computeIfAbsent(clientIp, this::createNewBucket);

		if (bucket.tryConsume(1)) {

			filterChain.doFilter(request, response);
		} else {

			response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
			response.getWriter().write("too many request. Please try again later.");
		}

	}

	private String getClientIp(HttpServletRequest request) {
		String forwarded = request.getHeader("X-Forwarded-For");
		if (forwarded != null) {
			return forwarded.split(",")[0];
		}
		return request.getRemoteAddr();
	}

	// Create a new bucket with a capacity of 100 requests and a refill rate of 10
	private Bucket createNewBucket(String string1) {

		Bandwidth limit = Bandwidth.builder().capacity(100).refillIntervally(10, Duration.ofMinutes(1)).build();
		return Bucket.builder().addLimit(limit).build();
	}

}
