package ru.cosmosway.web04.services;

import lombok.extern.java.Log;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.cosmosway.web04.entities.Request;
import ru.cosmosway.web04.entities.Coordinates;
import ru.cosmosway.web04.entities.SesssionUser;
import ru.cosmosway.web04.entitiesDTO.RequestDTO;
import ru.cosmosway.web04.entitiesDTO.CoordinatesDTO;
import ru.cosmosway.web04.exception.CoordinatesOutOfBoundsException;
import ru.cosmosway.web04.exception.EmptyCoordinateException;
import ru.cosmosway.web04.exception.SessionUserNotFoundException;
//import ru.cosmosway.web04.exceptions.CoordinatesOutOfBoundsException;
//import ru.cosmosway.web04.exceptions.EmptyCoordinateException;
//import ru.cosmosway.web04.exceptions.OwnerNotFoundException;
//import ru.cosmosway.web04.services.areaChecker.AreaChecker;
//import ru.cosmosway.web04.services.areaChecker.CheckerBuilder;
//import ru.cosmosway.web04.services.coordinatesValidator.CoordinatesValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log
@Service
public class RequestService {
    private final SesssionUserService service;
//    private final AreaChecker areaChecker;
//    private final CoordinatesValidator coordinatesValidator;

    public RequestService(SesssionUserService service
//            , CheckerBuilder checkerBuilder, CoordinatesValidator coordinatesValidator
    ) {
//        this.areaChecker = checkerBuilder
//                .initAreaChecker()
//                .addSquare1Quoter()
//                .addCircleIn3Quoter()
//                .addTriangleIn4Quoter()
//                .getChecker();
        this.service = service;
//        this.coordinatesValidator = coordinatesValidator;
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
//            coordinatesValidator.validate(coords); //if validation fails throws exceptions
            //update user by extra attempt
            Request newRequest = new Request(new Coordinates(coords.getX(), coords.getY(), coords.getR())
//                    , areaChecker.check(coords)
                    , true
            );
            SesssionUser newRequestUser = service.getUser(getCurrentUserLogin()); //un(log in) users can't addAttempts
            newRequest.setUser(newRequestUser);
            newRequest.getCoordinates().setRequest(newRequest);
            newRequestUser.getRequestList().add(newRequest);
            service.updateUser(newRequestUser);
            return new RequestDTO(coords.getX(), coords.getY(), coords.getR(), newRequest.getAreaIntersection());
        }catch (CoordinatesOutOfBoundsException e){
            return null;
        }
    }

    //todo: do i need this methods:
    // Attempt getAttempt();
    // Attempt replaceAttempt(Attempt newAttempt, Long id);
    // void deleteAttempt(Long id);

    public void deleteAllAttempts(){
        service.getUser(getCurrentUserLogin()).setRequestList(new ArrayList<>());
    }


    private String getCurrentUserLogin() {
        return "user1";
//        return (SecurityContextHolder.getContext().getAuthentication().getPrincipal()).toString(); //fixme: check that this cast is ok
    }
}