package pl.pollub.ISbackend.controller;

import lombok.Getter;

@Getter
public class AuthenticationRequest {
    private String username;
    private String password;

}