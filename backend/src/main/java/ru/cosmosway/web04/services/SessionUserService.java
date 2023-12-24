package ru.cosmosway.web04.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.cosmosway.web04.entities.SesssionUser;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.cosmosway.web04.entitiesDTO.SesssionUserDTO;
import ru.cosmosway.web04.repo.SessionUserRepository;
import ru.cosmosway.web04.exception.SessionUserAlreadyExistsException;
import ru.cosmosway.web04.exception.SessionUserNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;


@Log
@Service
@RequiredArgsConstructor
public class SessionUserService implements UserDetailsService {
    private final SessionUserRepository repository;
    private final BCryptPasswordEncoder passwordEncoder;

//    @Autowired
//    public SessionUserService(SessionUserRepository repository
////            , BCryptPasswordEncoder passwordEncoder
//    ) {
//        this.repository = repository;
////        this.passwordEncoder = passwordEncoder;
//    }

    public List<SesssionUser> allUsers() {
        return repository.findAll();
    }

    public SesssionUser addUser(SesssionUser newUser) {
        if(checkUser(newUser.getLogin()))
            throw  new SessionUserAlreadyExistsException(newUser.getLogin());
        return repository.save(newUser);
    }

    public void updateUser(SesssionUser newUser) {
        repository.save(newUser);
    }

    public SesssionUser getUser(String userLogin) {
        try {
            return repository.findById(userLogin).orElseThrow(() -> new SessionUserNotFoundException(userLogin));
        }catch (EntityNotFoundException e){
            throw new SessionUserNotFoundException(userLogin);
        }
    }
    public SesssionUser getUserViaAuth() {
        try {
            return repository.findById(getCurrentUserLogin()).orElseThrow(() -> new SessionUserNotFoundException(getCurrentUserLogin()));
        }catch (EntityNotFoundException e){
            throw new SessionUserNotFoundException(getCurrentUserLogin());
        }
    }

    public SesssionUser replaceUser(SesssionUser newUser, String userLogin) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return repository.findById(userLogin)
                .map(us -> {
                    us.setPassword(newUser.getPassword());
                    us.setRequestList(newUser.getRequestList()); //todo:check that here we put the whole object and check if it is authorized
                    return repository.save(us);
                })
                .orElseGet(() -> {
                    newUser.setLogin(userLogin);
                    return repository.save(newUser);
                });
    }

    public void deleteUser(String login) {
        repository.deleteById(login);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        SesssionUser user = getUser(login);
        if (Objects.isNull(user)) {
            throw new SessionUserNotFoundException(login);
        }
        return new User(user.getLogin(), user.getPassword(), true, true, true, true, new HashSet<>());
    }

    public SesssionUser getUserFromDTO(SesssionUserDTO userDTO){
        return new SesssionUser(userDTO.getLogin(), userDTO.getPassword());
    }

    public  boolean checkUser(String login){
        return repository.findById(login).isPresent();
    }

    private String getCurrentUserLogin() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }
}
