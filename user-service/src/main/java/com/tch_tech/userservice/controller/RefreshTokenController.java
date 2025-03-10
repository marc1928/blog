package com.tch_tech.userservice.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tch_tech.userservice.entity.User;
import com.tch_tech.userservice.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class RefreshTokenController {
    private final UserService userService;

    public RefreshTokenController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String authToken = request.getHeader("Authorization");
        if (authToken != null && authToken.startsWith("Bearer ")) {
            try {
                String jwt = authToken.substring(7);
                Algorithm algorithm = Algorithm.HMAC256("mySecretMarc123");
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                String username = decodedJWT.getSubject();

                Optional<User> user = userService.loadUserByUsername(username);

                String jwtAccessToken = JWT.create()
                        .withSubject(user.get().getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 8 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.get().getUserRole().stream().map(r -> r.getRoleName()).collect(Collectors.toList()))
                                .sign(algorithm);
                Map<String, String> idToken = new HashMap<>();
                idToken.put("access-token", jwtAccessToken);
                idToken.put("username", jwt);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }


        }else {
            throw new RuntimeException("Refresh Token required");
        }
    }
}
