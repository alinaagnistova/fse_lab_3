package ru.cosmosway.web04.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import lombok.extern.jackson.Jacksonized;

@NoArgsConstructor
@AllArgsConstructor
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

//    @JsonIgnore
    //if we want to drop all attempts after user death
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Attempt> attemptList = new ArrayList<>();

    public static SesssionUser initUser(){
        SesssionUser user = new SesssionUser();
//        user.setAttemptList(new ArrayList<>());
        return user;
    }

    @Override
    public String toString() {
        return "SesssionUser{" +
                "login=" + login +
                ", password=" + password +
//                ", attemptsIds=" + attemptList.stream().map(attempt -> attempt.getId().toString() + ", ") +
                '}';
    }
}
