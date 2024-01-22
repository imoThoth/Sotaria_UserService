package com.stockapi.loginRegister.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name="clientDetails")
public class Client implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id")
    private Integer clientID;
    @Column(name = "client_name", unique = true)
    private String username;
    @Column(name="client_email", unique = true)
    private String email;
    @Column(name="client_password")
    private String password;
    private Boolean locked = false;
    private Boolean enabled = true;
//    @ManyToMany TODO add roles
//    private Set<Role> roles;
    @Enumerated(EnumType.STRING)
    private Role appUserRole;
    public Client(){}

    public Client(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public Integer getClientID() {
        return this.clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

