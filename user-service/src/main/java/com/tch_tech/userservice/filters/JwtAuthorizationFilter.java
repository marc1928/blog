package com.tch_tech.userservice.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Order
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
       if (request.getServletPath().equals("/refreshToken")) {
           filterChain.doFilter(request, response);
       }else {
           String authorizationToken= request.getHeader("Authorization");
           if (authorizationToken != null && authorizationToken.startsWith("Bearer ")) {
               try {
                   String jwt = authorizationToken.substring(7);
                   Algorithm algorithm = Algorithm.HMAC256("mySecretMarc123");
                   JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                   DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                   String username = decodedJWT.getSubject();
                   String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
                   Collection<GrantedAuthority> authorities = new ArrayList<>();
                   for (String r:roles){
                       authorities.add(new SimpleGrantedAuthority(r));
                   }
                   UsernamePasswordAuthenticationToken authenticationToken =
                           new UsernamePasswordAuthenticationToken(username, null, authorities);
                   SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                   filterChain.doFilter(request, response);

               } catch (Exception e) {
                   response.setHeader("error-message", e.getMessage());
                   response.sendError(HttpServletResponse.SC_FORBIDDEN);
               }
           }else {
               filterChain.doFilter(request, response);
           }
       }
    }

}
