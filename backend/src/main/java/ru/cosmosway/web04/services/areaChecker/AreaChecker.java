package ru.cosmosway.web04.services.areaChecker;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import ru.cosmosway.web04.entitiesDTO.CoordinatesDTO;

import java.util.ArrayList;

@Service
public class AreaChecker {
    @Getter @Setter
    private ArrayList<AreaCheckCondition> listOfConditions;
    public AreaChecker(ArrayList<AreaCheckCondition> listOfConditions) {
        this.listOfConditions = listOfConditions;
    }

    public boolean check(@NotNull CoordinatesDTO coordinates) {
        double x = coordinates.getX();
        double y = coordinates.getY();
        double r = coordinates.getR();
        return listOfConditions.stream().anyMatch(condition -> condition.validate(x, y, r));
    }
}
