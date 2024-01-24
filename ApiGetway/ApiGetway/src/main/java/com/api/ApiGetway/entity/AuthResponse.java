package com.api.ApiGetway.entity;

import lombok.*;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuthResponse {
    private String userId;
    private String accessToken;
    private String refersToken;
    private long expireAt;
    private Collection<String> authorities;
}
