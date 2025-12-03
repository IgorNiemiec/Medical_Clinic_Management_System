package org.example.medical_clinic_management_system.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.medical_clinic_management_system.security.details.UserDetailsServiceImplementation;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private static final List<String> IGNORED_PATHS = Arrays.asList(
            "/api/auth/login",
            "/api/auth/register/user"
    );

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImplementation userDetailsService;

    private static final String ROLE_PREFIX = "ROLE_";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization") != null ?
                request.getHeader("Authorization").trim() :
                null;
        String jwt;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (authHeader.length() < 7) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7).trim().replaceAll("\\s", "");


        if (jwt.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        try {


            logger.info("Przygotowany token do parsowania (w cudzysłowach, bez spacji): \"{}\"", jwt);

            Claims claims = jwtUtil.extractAllClaims(jwt);
            userEmail = claims.getSubject();

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                if (jwtUtil.validateToken(jwt, userDetails)) {


                    @SuppressWarnings("unchecked")
                    List<String> authoritiesList = claims.get("authorities", List.class);

                    Collection<? extends GrantedAuthority> authorities;

                    if (authoritiesList != null && !authoritiesList.isEmpty()) {

                        authorities = authoritiesList.stream()
                                .map(role -> {
                                    String authority = role.toUpperCase();
                                    if (!authority.startsWith(ROLE_PREFIX)) {
                                        authority = ROLE_PREFIX + authority;
                                    }
                                    return new SimpleGrantedAuthority(authority);
                                })
                                .collect(Collectors.toList());

                        logger.info("JWT uwierzytelnione. Wczytane uprawnienia: {}", authorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.joining(", ")));

                    } else {

                        authorities = userDetails.getAuthorities();
                    }

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            authorities
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));


                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (ExpiredJwtException e) {
            System.err.println("JWT error: Token wygasł dla żądania: " + request.getRequestURI());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("JWT error: Token wygasł. Zaloguj się ponownie.");
            return;
        } catch (MalformedJwtException | SignatureException e) {

            logger.error("JWT Signature/Malformed error for request to {}: {}", request.getRequestURI(), e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("JWT error: Niepoprawny podpis klucza lub struktura tokena. Proszę upewnić się, że serwer został ZRESTARTOWANY po zmianie klucza tajnego.");
            return;
        } catch (Exception e) {

            logger.error("JWT authentication failed for request to " + request.getRequestURI(), e);

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Invalid JWT token.");
            return;
        }


        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return IGNORED_PATHS.stream()
                .anyMatch(path -> path.equals(request.getServletPath()));
    }
}