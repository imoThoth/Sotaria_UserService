package com.stockapi.loginRegister.controller;

import com.stockapi.loginRegister.dto.LoginDTO;
import com.stockapi.loginRegister.dto.RegisterDTO;
import com.stockapi.loginRegister.entity.Client;
import com.stockapi.loginRegister.repository.ClientRepository;
import com.stockapi.loginRegister.services.ClientAccessService;
import com.stockapi.loginRegister.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class ClientAuthController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    ClientAccessService clientAccessService;
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticateUser;
    @Autowired
    PasswordEncoder passwordEncoder;
    Logger logger = LoggerFactory.getLogger(ClientAuthController.class);

    @GetMapping("/hello")
    public String helloWorld(){
        return "Success";
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDTO loginDTO){

        try{

            Authentication auth = authenticateUser.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
            if(!auth.isAuthenticated()) {
                return new ResponseEntity<>("User Not Found In Database", HttpStatus.OK);
            }
        }catch (BadCredentialsException badCredentialsException){
            return new ResponseEntity<>("User Login Was Unsuccessful", HttpStatus.OK);
        }


        return new ResponseEntity<>("User Login Successful", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerClient(
            @RequestBody RegisterDTO registerDTO
            ){
        //check if user exist in database

        Optional<Client> booleanCheck = clientRepository.findByUsername(registerDTO.getClientName());
        String returnedMessage = "";

        if(booleanCheck.isEmpty()){
            returnedMessage = clientAccessService.addRegisteredUser(registerDTO);
        }else {
            return new ResponseEntity<>("Username already exist", HttpStatus.BAD_REQUEST);
        }

        if(returnedMessage.equals("Success")){
            return  new ResponseEntity<>("User Succesfully Registered", HttpStatus.OK);
        }

        return  new ResponseEntity<>("Error Creating User", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
