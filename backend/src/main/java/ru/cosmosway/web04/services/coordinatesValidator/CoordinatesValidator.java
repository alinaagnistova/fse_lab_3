package ru.cosmosway.web04.services.coordinatesValidator;

import org.springframework.stereotype.Service;
import ru.cosmosway.web04.entitiesDTO.CoordinatesDTO;
import ru.cosmosway.web04.exception.CoordinatesOutOfBoundsException;
import ru.cosmosway.web04.exception.EmptyCoordinateException;

import java.util.Optional;

@Service
public class CoordinatesValidator {
    public void validate(CoordinatesDTO coordinates){
        double x = Optional.of(coordinates.getX()).orElseThrow(() -> new EmptyCoordinateException("x"));
        double y = Optional.of(coordinates.getY()).orElseThrow(() -> new EmptyCoordinateException("y"));
        double r = Optional.of(coordinates.getR()).orElseThrow(() -> new EmptyCoordinateException("r"));


        if (!(x >= -5 && x <= 3)) {
            throw new CoordinatesOutOfBoundsException(x, "x");
        }
        if (!(y >= -3 && y <= 3)) {
            throw new CoordinatesOutOfBoundsException(y, "y");
        }
        if (!(r >= -5 && r <= 3)) {
            throw new CoordinatesOutOfBoundsException(r, "r");
        }
    }
}
