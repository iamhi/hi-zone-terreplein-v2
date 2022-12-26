package com.github.iamhi.hizone.terreplein.v2.config.providers;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class HiZoneAuthFilter extends OncePerRequestFilter {

    private static final String BEARER_PREFIX = "Bearer ";

    private final AuthenticationManager authenticationManager;

    private boolean isValidAuthorizationHeader(String authorizationHeader) {
        return StringUtils.isNotBlank(authorizationHeader) &&
            authorizationHeader.contains(BEARER_PREFIX);
    }

    private String getTokenFromHeader(String authorizationHeader) {
        return authorizationHeader.split(BEARER_PREFIX)[1];
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (isValidAuthorizationHeader(authorization)) {
            String token = getTokenFromHeader(authorization);

            try {

                Authentication authentication = authenticationManager.authenticate(new HiZoneAuthenticationToken(token));

                if (authentication != null) {
                    authentication.setAuthenticated(true);

                    SecurityContext context = SecurityContextHolder.createEmptyContext();

                    context.setAuthentication(authentication);

                    SecurityContextHolder.setContext(context);
                }
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        }

        filterChain.doFilter(request, response);
    }
}
