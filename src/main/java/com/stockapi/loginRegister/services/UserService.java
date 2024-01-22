package com.stockapi.loginRegister.services;

import com.stockapi.loginRegister.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    ClientRepository clientRepository;

    Logger userServiceLogger = LoggerFactory.getLogger(UserService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userServiceLogger.info("Inside UserService Class: Load User By UserName");

        return clientRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User " + username + " Was Not Found In Database"));
    }

}
