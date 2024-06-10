package pl.pollub.ISbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.ISbackend.model.Role;
import pl.pollub.ISbackend.model.User;
import pl.pollub.ISbackend.service.UserService;
import pl.pollub.ISbackend.util.JwtUtil;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }

        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());

        Map<String, String> response = Map.of("token", jwtUtil.generateToken(userDetails), "username", authenticationRequest.getUsername());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON) // return json
                .body(new ObjectMapper().writeValueAsString(response));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        if(userService.findByUsername(authenticationRequest.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .build();
        }

        User user = new User();
        user.setUsername(authenticationRequest.getUsername());
        user.setPassword(authenticationRequest.getPassword());
        user.setRoles(Role.USER.name());

        user = userService.save(user);

        final UserDetails userDetails = userService.loadUserByUsername(user.getUsername());

        Map<String, String> response = Map.of("token", jwtUtil.generateToken(userDetails), "username", user.getUsername());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON) // return json
                .body(new ObjectMapper().writeValueAsString(response));
    }

    @PostMapping("/authorize")
    public ResponseEntity<Object> verifyToken(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String jwt = authorizationHeader.substring(7);

        try {
            String username = jwtUtil.extractUsername(jwt);
            UserDetails userDetails = userService.loadUserByUsername(username);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(Map.of("username", username, "token", jwtUtil.generateToken(userDetails)));
        } catch (ExpiredJwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
