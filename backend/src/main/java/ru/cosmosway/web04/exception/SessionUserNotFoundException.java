package ru.cosmosway.web04.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SessionUserNotFoundException extends UsernameNotFoundException {
    public SessionUserNotFoundException(String login) {
        super("User with login: \"" + login + "\" wasn't found!");
    }
}
