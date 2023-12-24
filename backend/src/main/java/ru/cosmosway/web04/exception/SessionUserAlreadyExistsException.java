package ru.cosmosway.web04.exception;

public class SessionUserAlreadyExistsException extends RuntimeException{
    public SessionUserAlreadyExistsException(String login) {
        super("User with login: " + login + " already exists!");
    }
}
