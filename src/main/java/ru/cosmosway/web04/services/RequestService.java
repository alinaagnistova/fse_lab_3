package ru.cosmosway.web04.services;

import lombok.extern.java.Log;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import ru.cosmosway.web04.entities.Request;
import ru.cosmosway.web04.entities.Coordinates;
import ru.cosmosway.web04.entities.SesssionUser;
import ru.cosmosway.web04.entitiesDTO.RequestDTO;
import ru.cosmosway.web04.entitiesDTO.CoordinatesDTO;
import ru.cosmosway.web04.exception.CoordinatesOutOfBoundsException;
import ru.cosmosway.web04.exception.EmptyCoordinateException;
import ru.cosmosway.web04.exception.SessionUserNotFoundException;
import ru.cosmosway.web04.exception.CoordinatesOutOfBoundsException;
import ru.cosmosway.web04.exception.EmptyCoordinateException;
import ru.cosmosway.web04.exception.SessionUserNotFoundException;
import ru.cosmosway.web04.services.areaChecker.AreaChecker;
import ru.cosmosway.web04.services.areaChecker.CheckerBuilder;
import ru.cosmosway.web04.services.coordinatesValidator.CoordinatesValidator;

import java.util.ArrayList;
import java.util.List;

@Log
@Service
public class RequestService {
    private final SessionUserService service;
    private final AreaChecker areaChecker;
    private final CoordinatesValidator coordinatesValidator;

    public RequestService(SessionUserService service
            , CheckerBuilder checkerBuilder, CoordinatesValidator coordinatesValidator
    ) {
        this.areaChecker = checkerBuilder
                .initAreaChecker()
                .addSquare1Quoter()
                .addCircleIn3Quoter()
                .addTriangleIn4Quoter()
                .getChecker();
        this.service = service;
        this.coordinatesValidator = coordinatesValidator;
    }

    public List<RequestDTO> getAllRequests() {
        List<RequestDTO> resultList = new ArrayList<>();
        service.getUser(getCurrentUserLogin()).getRequestList().forEach(request -> {
                    Coordinates c = request.getCoordinates();
                    resultList.add(new RequestDTO(c.getX(), c.getY(), c.getR(), request.getAreaIntersection()));
                }
        );
        return resultList;
    }

    public RequestDTO addRequest(CoordinatesDTO coords) throws EmptyCoordinateException, CoordinatesOutOfBoundsException, SessionUserNotFoundException
{
        try {
            coordinatesValidator.validate(coords);
            Request newRequest = new Request(new Coordinates(coords.getX(), coords.getY(), coords.getR())
                    , areaChecker.check(coords)
            );
            SesssionUser newRequestUser = service.getUser(getCurrentUserLogin());
            newRequest.setUser(newRequestUser);
            newRequest.getCoordinates().setRequest(newRequest);
            newRequestUser.getRequestList().add(newRequest);
            service.updateUser(newRequestUser);
            return new RequestDTO(coords.getX(), coords.getY(), coords.getR(), newRequest.getAreaIntersection());
        }catch (CoordinatesOutOfBoundsException e){
            return null;
        }
    }
    public void deleteAllRequests(){
        service.getUser(getCurrentUserLogin()).setRequestList(new ArrayList<>());
    }

    private String getCurrentUserLogin() {
        return ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }
}