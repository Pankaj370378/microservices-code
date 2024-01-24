package com.api.ApiGetway.controller;

import com.api.ApiGetway.entity.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(name = "/auth")
public class AuthController {
    private Logger logger= LoggerFactory.getLogger(AuthController.class);
    @GetMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,  // Injects the OAuth2 client for Okta
            @AuthenticationPrincipal OidcUser user,           // Injects authenticated user details
            Model model                                         // Model for view rendering (unused here)
    ) {
        logger.info("User email ID: {}", user.getEmail());    // Logs user's email for informational purposes

        // Create a response object to encapsulate authentication information
        AuthResponse authResponse = new AuthResponse();

        // Set user-specific information in the response
        authResponse.setUserId(user.getEmail());              // Set user ID (using email as identifier)
        authResponse.setAccessToken(client.getAccessToken().getTokenValue()); // Set access token
        authResponse.setRefersToken(client.getRefreshToken().getTokenValue()); // Set refresh token
        authResponse.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond()); // Set access token expiration time

        // Extract user's granted authorities (roles or permissions)
        List<String> authorities = user.getAuthorities().stream().map(grantedAuthority -> {
            return grantedAuthority.getAuthority();
        }).collect(Collectors.toList());           // Set authorities in the response
        authResponse.setAuthorities(authorities);
        // Return a successful response with the authentication details
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

}
