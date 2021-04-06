package com.notes.Notes.service;

import com.notes.Notes.Security.services.UserDetailsImpl;
import com.notes.Notes.model.Users;
import com.notes.Notes.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Users user = usersRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return UserDetailsImpl.build(user);
    }
}
