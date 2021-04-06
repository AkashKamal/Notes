package com.notes.Notes.api;

import com.notes.Notes.model.Notes;
import com.notes.Notes.model.Users;
import com.notes.Notes.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UsersController {

    @Autowired
    UsersService usersService;

    @PostMapping("/api/v1/addUser")
    public String addNotes(@RequestBody Users user)
    {
        usersService.addUser(user);
        return "success";
    }

    @GetMapping("/users/{userID}")
    public Optional<Users> getNoteById(@PathVariable long notesId)
    {
        return usersService.getUserDetailsbyID(notesId);
    }

}
