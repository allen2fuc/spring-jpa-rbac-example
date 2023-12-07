package com.example.example.controller;

import com.example.example.helper.JwtHelper;
import com.example.example.RespResult;
import com.example.example.helper.UserDetailsCacheHelper;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public RespResult<String> login(@Validated @RequestBody LoginRequest loginRequest) {
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(), loginRequest.password());
        Authentication authenticationResponse =
                this.authenticationManager.authenticate(authenticationRequest);
        String token = JwtHelper.generateToken(authenticationResponse.getName());
        UserDetailsCacheHelper.putUserCache((UserDetails) authenticationResponse.getPrincipal());
        return RespResult.success(token);
    }

    public record LoginRequest(@NotBlank String username, @NotBlank String password) {
    }
}