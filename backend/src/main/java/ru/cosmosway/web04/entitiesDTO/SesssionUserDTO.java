package ru.cosmosway.web04.entitiesDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SesssionUserDTO {
    private String login;
    private String password;


    @Override
    public String toString() {
        return "SesssionUserDTO{" +
                "login=" + login +
                ", password=" + password +
                '}';
    }
}
