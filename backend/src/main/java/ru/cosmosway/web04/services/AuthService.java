package ru.cosmosway.web04.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthService {
    private final SessionUserService userSrv;
    public AuthService(SessionUserService userSrv)
    {
        this.userSrv = userSrv;
    }
}
