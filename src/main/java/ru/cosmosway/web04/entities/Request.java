package ru.cosmosway.web04.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

import java.util.Objects;

import jakarta.persistence.*;
import java.util.Objects;

@Log
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "requests")
public class Request {

    @MapsId
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "coordinates_id", nullable = false)
    private Coordinates coordinates = new Coordinates(0.0, 0.0, 01.0);
    private boolean areaIntersection = true;

    @Id
    @PrimaryKeyJoinColumn
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = null;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private SesssionUser user = null;

    public Request(Coordinates coordinates, boolean areaIntersection) {
        this.coordinates = coordinates;
        this.areaIntersection = areaIntersection;
        this.user = null;
        this.id = null;
    }

    public static Request initRequest() {
        Request request = new Request();
        request.setCoordinates(new Coordinates());
        request.setUser(new SesssionUser());
        return request;
    }

    @Override
    public String toString() {
        return "Request{" +
                "coordinates: (x=" + coordinates.getX() +
                "; y=" + coordinates.getY() +
                "; r=" + coordinates.getR() +
                "), areaIntersection=" + areaIntersection +
                ", id=" + id +
                ", user isn't null=" + (Objects.nonNull(user)) +
                " " + System.currentTimeMillis() +
                '}';
    }

    public void setCoordinates(Coordinates coordinates) {
        log.info("Timestamp(setCoordinates) " + System.currentTimeMillis() + " " + coordinates.toString());
        this.coordinates = coordinates;
    }


    public boolean getAreaIntersection() {
        return areaIntersection;
    }
}

