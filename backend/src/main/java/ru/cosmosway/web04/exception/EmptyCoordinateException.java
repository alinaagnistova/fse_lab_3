package ru.cosmosway.web04.exception;

public class EmptyCoordinateException extends RuntimeException{
    public EmptyCoordinateException(String coordinateName){
        super("Coordinate " + coordinateName + " is empty!");
    }
}
