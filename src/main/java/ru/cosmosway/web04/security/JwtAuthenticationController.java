package ru.cosmosway.web04.security;

import java.util.Objects;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import ru.cosmosway.web04.services.SessionUserDetails;

//import com.javainuse.config.JwtTokenUtil;
//import com.javainuse.model.JwtRequest;
//import com.javainuse.model.JwtResponse;

@Log
@RestController
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private SessionUserDetails sessionUserDetails;
//    @CrossOrigin
@RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws Exception {
    log.info(authenticationRequest.toString());
    log.info("createAuthenticationToke1111n!!!!!§!");

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
    log.info("createAuthenticationToken!!!!!§!");

        final UserDetails userDetails = sessionUserDetails
                .loadUserByUsername(authenticationRequest.getUsername());
    log.info("createAuthenticationToken!!!!22222!!");

//    final String token = jwtTokenUtil.generateToken(userDetails);
//        log.info("createAuthenticationToken!!!!!!");

        return ResponseEntity.ok("ok");
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

