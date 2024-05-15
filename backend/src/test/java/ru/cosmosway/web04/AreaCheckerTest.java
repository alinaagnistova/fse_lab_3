package ru.cosmosway.web04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.cosmosway.web04.entitiesDTO.CoordinatesDTO;
import ru.cosmosway.web04.services.areaChecker.AreaChecker;
import ru.cosmosway.web04.services.areaChecker.CheckerBuilder;

import static org.junit.jupiter.api.Assertions.*;

class AreaCheckerTest {
    private CheckerBuilder builder;
    private AreaChecker checker;

    @BeforeEach
    void setUp() {
        builder = new CheckerBuilder();
        checker = builder.initAreaChecker()
                .addTriangleIn4Quoter()
                .addCircleIn3Quoter()
                .addSquare1Quoter()
                .getChecker();
    }

    @Test
    void testTriangleIn4QuoterTrue() {
        CoordinatesDTO coordinates = new CoordinatesDTO();
        coordinates.setX(0.5);
        coordinates.setY(-0.5);
        coordinates.setR(1);
        assertTrue(checker.check(coordinates), "Point should be inside the triangle");
    }

    @Test
    void testCircleIn3QuoterTrue() {
        CoordinatesDTO coordinates = new CoordinatesDTO();
        coordinates.setX(-0.25);
        coordinates.setY(-0.25);
        coordinates.setR(1);
        assertTrue(checker.check(coordinates), "Point should be inside the circle");
    }


    @Test
    void testSquare1QuoterTrue() {
        CoordinatesDTO coordinates = new CoordinatesDTO();
        coordinates.setX(0.5);
        coordinates.setY(0.5);
        coordinates.setR(1);
        assertTrue(checker.check(coordinates), "Point should be inside the square");
    }

    @Test
    void testPointOutsideAllAreasFalse() {
        CoordinatesDTO coordinates = new CoordinatesDTO();
        coordinates.setX(-1);
        coordinates.setY(-1);
        coordinates.setR(1);
        assertFalse(checker.check(coordinates), "Point should be outside all areas");
    }
}