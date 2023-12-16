//package ru.cosmosway.web04.rest;
//
//import lombok.extern.java.Log;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import ru.cosmosway.web04.entitiesDTO.SesssionUserDTO;
//import ru.cosmosway.web04.security.SecurityConstants;
//import ru.cosmosway.web04.services.AuthorizationService;
//
//@Log
//@RestController
//public class AuthController {
//
//    private final AuthorizationService authorizationService;
//
//    public AuthController(AuthorizationService authorizationService) {
//        this.authorizationService = authorizationService;
//    }
//
//    @PostMapping(value = SecurityConstants.SIGN_UP_URL, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public String registration(@RequestBody SesssionUserDTO userDTO) {
//        log.info("Starting registration!");
//        return authorizationService.register(userDTO);
//    }
//
//
//
//}