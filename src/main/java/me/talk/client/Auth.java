package me.talk.client;

import lombok.Data;

@Data
public class Auth {
    private String access_token;
    private Long expires_in;
    private String token_type;
    private String scope;
    private String refresh_token;
    private String username;
    private String first_name;
    private String last_name;
    private String language;
    private Long id;
    private String group;
    private String code;
}
