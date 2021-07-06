package com.notes.Notes.api;

import com.notes.Notes.Security.jwt.JwtUtils;
import com.notes.Notes.exception.AuthorizationException;
import com.notes.Notes.exception.ErrorCode;
import com.notes.Notes.model.Users;
import com.notes.Notes.repository.UsersRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.xml.ws.Response;

@CrossOrigin(origins={ "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UsersRepository userRepository;


    @PostMapping(path = "/signin")
    public ResponseEntity<String> authenticate(@Validated @RequestBody Users user) throws AuthorizationException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            JSONObject response = new JSONObject();
            response.put("jwt", jwt);
            return ResponseEntity.ok(response.toString());
        }
        catch (BadCredentialsException e)
        {
           throw new AuthorizationException(ErrorCode.INVALID_CREDENTIALS);
        }
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<?> register(@Validated @RequestBody Users user) throws AuthorizationException
    {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
           throw new AuthorizationException(ErrorCode.USER_EXISTS);
        }

        Users newUser = new Users(user.getEmail(),encoder.encode(user.getPassword()));
        userRepository.save(newUser);
        return ResponseEntity.ok("Users added");
    }
}
