package io.mountblue.redditclone.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class JwtResponse {

    private String jwtToken;

    private String username;
}
