package com.notes.Notes.Security.services;

import com.notes.Notes.model.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


//This class basically builds UserDetailsImpl that extends spring security class (used for spring authentication) from our own user POJO class.
public class UserDetailsImpl implements UserDetails {

    private long id;
    private String email;
    private String password;

    public UserDetailsImpl(long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }


    public static UserDetailsImpl build(Users user)
    {
        return new UserDetailsImpl(user.getId(),user.getEmail(),user.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
