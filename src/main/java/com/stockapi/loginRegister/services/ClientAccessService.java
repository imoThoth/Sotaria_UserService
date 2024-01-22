package com.stockapi.loginRegister.services;

import com.stockapi.loginRegister.dto.LoginDTO;
import com.stockapi.loginRegister.dto.RegisterDTO;
import com.stockapi.loginRegister.entity.Client;
import com.stockapi.loginRegister.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClientAccessService
{

    @Autowired
    ClientRepository clientRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    Logger logger = LoggerFactory.getLogger(ClientAccessService.class);


    //TODO - currently not being used... Auth in controller is currently handling login
    public LoginDTO userLogin(String username, String password){

            //pass username and password... and use auth manager to authenticate
            //if user exist provide jwt else throw exception
            Client user = clientRepo.findByUsername(username).get();

            if(user == null){
                throw new UsernameNotFoundException("User: " + username + " Does Not exist");
            }

            if(!Objects.equals(user.getPassword(), password)){
                throw new UsernameNotFoundException("User: " + username + " Does Not exist");
            }
            //Generate jwt token
            return new LoginDTO();

    }


    public String addRegisteredUser(RegisterDTO registerDTO){

        Client client = new Client();
        client.setUsername(registerDTO.getClientName());
        client.setEmail(registerDTO.getClientEmail());
        client.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        clientRepo.save(client);

        System.out.println("Added New User: "  + client.getUsername());

        return "Success";
    }

}



//    /**
//     * Configuration to load client details if it exist in database
//     * Method looksup username and password and grants relevant authorities
//     * @param username
//     * @return
//     * @throws UsernameNotFoundException
//     */
//
//    public UserDetails loadUserByclientName(String username) throws UsernameNotFoundException {
//
//        Optional<Client> client = clientRepo.findByUsername(username);
//
//         if(client.isEmpty()){
//            logger.info("Username not found");
//            throw new UsernameNotFoundException("User: " + username + " Does Not exist");
//        }
//
//        Authentication auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, client.get().getPassword()));
//
//
//        UserDetails user = User.withUsername(client.get().getUsername())
//                .password(client.get().getPassword())
//                .authorities("USER") //TODO load roles/permission from database
//                .build();
//
//
//        if(auth.isAuthenticated()){
//            return user;
//        }
//
//        return new UserDetails() {
//            @Override
//            public Collection<? extends GrantedAuthority> getAuthorities() {
//                return null;
//            }
//
//            @Override
//            public String getPassword() {
//                return null;
//            }
//
//            @Override
//            public String getUsername() {
//                return null;
//            }
//
//            @Override
//            public boolean isAccountNonExpired() {
//                return false;
//            }
//
//            @Override
//            public boolean isAccountNonLocked() {
//                return false;
//            }
//
//            @Override
//            public boolean isCredentialsNonExpired() {
//                return false;
//            }
//
//            @Override
//            public boolean isEnabled() {
//                return false;
//            }
//        };
//    }


//    public Client loadUserByUsername(String username)
////            throws
////            UsernameNotFoundException
//    {
//
//        Client client = clientRepo.findByClientNameOrEmail(username, username);
//
//        if(client == null){
////            throw new UsernameNotFoundException("User: " + username + " Does Not exist");
//            throw new RuntimeException("\"User: \" + username + \" Does Not exist\"");
//        }
//
////        UserDetails user = User.withUsername(client.getEmail())
////                .password(client.getPassword())
////                .authorities("USER") //TODO load roles/permission from database
////                .build();
//
//        return client;
//    }
