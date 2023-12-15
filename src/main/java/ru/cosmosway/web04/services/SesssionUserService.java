package ru.cosmosway.web04.services;

import lombok.AllArgsConstructor;
import ru.cosmosway.web04.entities.SesssionUser;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ru.cosmosway.web04.entitiesDTO.SesssionUserDTO;
import ru.cosmosway.web04.repo.SessionUserRepository;
//import weblab4.exceptions.UserAlreadyExistsException;
//import weblab4.exceptions.UserNotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Log
@Service
public class SesssionUserService implements UserDetailsService {
    private final SessionUserRepository repository;
//    private final BCryptPasswordEncoder passwordEncoder; //FIXME this field needed only for tests

    @Autowired
    public SesssionUserService(SessionUserRepository repository) {
        this.repository = repository;
//        this.passwordEncoder = passwordEncoder;
        //addUserTest(); //todo: remove test
    }

    public List<SesssionUser> allUsers() {
        return repository.findAll();
    }

    public SesssionUser addUser(SesssionUser newUser) {
//        if(checkUser(newUser.getLogin()))
//            throw Exception;
//            throw  new SesssionUserAlreadyExistsException(newUser.getLogin());
        return repository.save(newUser);
    }

    public void updateUser(SesssionUser newUser) {
        repository.save(newUser);
    }

    public Optional<SesssionUser> getUser(String userLogin) {
//        try {
            return Optional.of(repository.findById(userLogin).orElseThrow());
//                    .orElseThrow(() -> new SesssionUserNotFoundException(userLogin));
//        }catch (EntityNotFoundException e){
//            throw new SesssionUserNotFoundException(userLogin);
//        }
    }

//    public SesssionUser replaceUser(User newUser, String userLogin) {
//
//        return repository.findById(userLogin);
////                .map(user -> {
////                    user.setPassword(newUser.getPassword());
////                    user.setAttemptList(newUser.getAttemptList()); //todo:check that here we put the whole object and check if it is authorized
////                    return repository.save(user);
////                })
////                .orElseGet(() -> {
////                    newUser.setLogin(userLogin);
////                    return repository.save(newUser);
////                });
//    }

    public void deleteUser(String login) {
        repository.deleteById(login);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        SesssionUser user = getUser(login).orElseThrow();
//        if (Objects.isNull(user)) {
//            throw new UserNotFoundException(login);
//        }
        return new User(user.getLogin(), user.getPassword(), true, true, true, true, new HashSet<>());
    }

    public SesssionUser getUserFromDTO(SesssionUserDTO userDTO){
        return new SesssionUser(userDTO.getLogin(), userDTO.getPassword());
    }

    public  boolean checkUser(String login){
        return repository.findById(login).isPresent();
    }
//    private void addUserTest() { //todo: remove test
////        Coordinates testCoordinates = new Coordinates(7, 7, 7);
////        Attempt testAttempt = new Attempt(testCoordinates, true);
////        SesssionUser testUser = new SesssionUser("liv", "marsen");
////        testUser.getAttemptList().add(testAttempt);
////        testAttempt.setUser(testUser);
////        testCoordinates.setAttempt(testAttempt);
////        repository.save(testUser);
//        SesssionUser user = new SesssionUser("M","3");
//        addUser(user);
////        try {
//            log.info("Trying to find user with id:\"" + user.getLogin() +"\".");
//            SesssionUser bdUser = getUser(user.getLogin());
//            log.info(bdUser.toString());
////        }catch (UserNotFoundException e){
////            log.warning(e.getLocalizedMessage());
////        }
//    }
}
