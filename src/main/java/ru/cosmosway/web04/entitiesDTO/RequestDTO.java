package ru.cosmosway.web04.entitiesDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Log
@Getter
@Setter
@AllArgsConstructor
public class RequestDTO {
    private double x;
    private double y;
    private double r;
    private boolean areaIntersection;
    private String time;
    private String execTime;
    @Override
    public String toString() {
        return "Request{" +
                "coordinates{" +
                "(x=" + x +
                "; y=" + y +
                "; r=" + r +
                "),doFitArea=" + areaIntersection +
                '}';
    }
}
