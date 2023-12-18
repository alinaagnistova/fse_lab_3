package ru.cosmosway.web04.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cosmosway.web04.entitiesDTO.RequestDTO;
import ru.cosmosway.web04.entitiesDTO.CoordinatesDTO;
import ru.cosmosway.web04.services.RequestService;

import java.util.List;


@RestController
@CrossOrigin
public class RequestController {
    private final RequestService service;
    @Autowired
    public RequestController(RequestService service) {
        this.service = service;
    }

    @GetMapping("/requests")
    List<RequestDTO> getAllRequests() {
        return service.getAllRequests();
    }

    @PostMapping("/requests")
    RequestDTO addRequest(@RequestBody CoordinatesDTO newCoordinates) {
        return service.addRequest(newCoordinates);
    }

    @DeleteMapping("/requests")
    void deleteAllAttempt() {
        service.deleteAllRequests();
    }
}
