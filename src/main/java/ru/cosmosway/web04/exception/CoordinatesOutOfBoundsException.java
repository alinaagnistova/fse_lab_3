package ru.cosmosway.web04.exception;

public class CoordinatesOutOfBoundsException extends RuntimeException{
    public CoordinatesOutOfBoundsException(double coordinateValue, String coordinateName) {
        super("Coordinate " + coordinateName + " is out of bounds. It's value is = " + coordinateValue + ".");
    }
}