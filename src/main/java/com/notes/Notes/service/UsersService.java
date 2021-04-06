package com.notes.Notes.service;

import com.notes.Notes.model.Notes;
import com.notes.Notes.model.Users;
import com.notes.Notes.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    public void addUser(Users user)
    {
        usersRepository.save(user);
    }

    public List<Users> getAllUsers()
    {

        return usersRepository.findAll();
    }

    public Optional<Users> getUserDetailsbyID(long id)
    {
       return  usersRepository.findById(id);
    }

}
