package ru.cosmosway.web04.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import jakarta.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "coordinates")
public class Coordinates{
    private double x = 0.0;
    private double y = 0.0;
    private double r = 0.0;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @OneToOne(mappedBy = "coordinates", cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Request request;

    public Coordinates(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.id = null;
        this.request = null;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "(x=" + x +
                "; y=" + y +
                "; r=" + r +
                "), id=" + id +
                ", attempts isn't null=" + (Objects.nonNull(request)) +
                '}';
    }
}
