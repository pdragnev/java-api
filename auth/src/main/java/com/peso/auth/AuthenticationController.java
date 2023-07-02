package com.peso.auth;

import com.peso.config.KafkaProducerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    private final KafkaProducerService kafkaProducerService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        AuthenticationResponse authenticationResponse = authenticationService.register(request);
        kafkaProducerService.produceNotification("Register notification for email " + request.getEmail());
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);
        kafkaProducerService.produceNotification("Auth notification for email " + request.getEmail());
        return ResponseEntity.ok(authenticationResponse);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }

    @GetMapping("/validate-token")
    public void validateToken(
            HttpServletRequest request
    ) {
        authenticationService.validateToken(request);
        kafkaProducerService.produceNotification("Validated authorization header " + request.getHeader("Authorization"));
    }
}
