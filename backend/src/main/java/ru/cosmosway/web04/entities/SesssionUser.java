package ru.cosmosway.web04.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.jackson.Jacksonized;
import ru.cosmosway.web04.rest.RequestController;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Getter
@Setter
@Jacksonized
@Table(name = "users")
public class SesssionUser {
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;

    @Id
    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Request> requestList = new ArrayList<>();

    public SesssionUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static SesssionUser initUser(){
        SesssionUser user = new SesssionUser();
        user.setRequestList(new ArrayList<>());
        return user;
    }

    @Override
    public String toString() {
        return "SesssionUser{" +
                "login=" + login +
                ", password=" + password +
                ", requestsIds=" + requestList.stream().map(request -> request.getId().toString() + ", ") +
                '}';
    }
}
