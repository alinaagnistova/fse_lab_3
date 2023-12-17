package ru.cosmosway.web04.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ru.cosmosway.web04.entities.SesssionUser;

@Log
@Service
@RequiredArgsConstructor
public class SessionUserDetails
        implements UserDetailsService
//    implements AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken>
{

    private final SesssionUserService service;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        SesssionUser user = service.getUser(login);

        return User
                .withUsername(login)
                .password(user.getPassword())
                .roles("USER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
