package ru.cosmosway.web04.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cosmosway.web04.entities.SesssionUser;
import ru.cosmosway.web04.services.SessionUserService;
import java.util.List;

@RestController
@CrossOrigin
public class SessionUserController {
    private final SessionUserService service;
    @Autowired
    public SessionUserController(SessionUserService service) {
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

    @GetMapping("/users/{login}")
    SesssionUser getUser(@PathVariable String login) {
        return service.getUser(login);
    }

    @GetMapping("/users/profile")
    SesssionUser getUserProfile() {
        return service.getUserViaAuth();
    }

    @PutMapping("/users/{login}")
    SesssionUser replaceUser(@RequestBody SesssionUser newUser, @PathVariable String login) {
        return service.replaceUser(newUser, login);
    }
    @DeleteMapping("/users/{login}")
    void deleteAttempt(@PathVariable String login) {
        service.deleteUser(login);
    }
}
