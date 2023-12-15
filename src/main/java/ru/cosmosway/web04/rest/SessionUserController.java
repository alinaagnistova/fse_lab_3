package ru.cosmosway.web04.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cosmosway.web04.entities.SesssionUser;
import ru.cosmosway.web04.services.SesssionUserService;
import java.util.List;
//
@RestController
public class SessionUserController {
    private final SesssionUserService service;
    @Autowired
    public SessionUserController(SesssionUserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    List<SesssionUser> all() {
        return service.allUsers();
    }

    @PostMapping("/users")
    SesssionUser addUser(@RequestBody SesssionUser newUser) {
        return service.addUser(newUser);
    }

    // Single item
    @GetMapping("/users/{login}")
    SesssionUser getUser(@PathVariable String login) {
        return service.getUser(login).orElseThrow();
    }

//    @PutMapping("/users/{login}")
//    SesssionUser replaceUser(@RequestBody SesssionUser newUser, @PathVariable String login) {
//        return service.replaceUser(newUser, login);
//
//    }

    @DeleteMapping("/users/{login}")
    void deleteAttempt(@PathVariable String login) {
        service.deleteUser(login);
    }
}
