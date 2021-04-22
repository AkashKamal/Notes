package com.notes.Notes.service;

import com.notes.Notes.Security.services.UserDetailsImpl;
import com.notes.Notes.model.Notes;
import com.notes.Notes.model.Users;
import com.notes.Notes.repository.NotesRepository;
import com.notes.Notes.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotesService {

    @Autowired
    NotesRepository notesRepository;

    @Autowired
    UsersRepository usersRepository;

    public void addNotes(Notes notes)
    {
        long currentMilliSeconds = System.currentTimeMillis();
        Date now = new Date(currentMilliSeconds);
        notes.setAddedTime(now);
        UserDetailsImpl user = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Users> userObj = usersRepository.findById(user.getid());
        notes.setUser(userObj.get());
        notesRepository.save(notes);
    }

    public List<Notes> getAllNotes()
    {
        UserDetailsImpl user = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<Users> userObj = usersRepository.findById(user.getid());
        return notesRepository.findNotesByUser(userObj.get());
    }

    public Optional<Notes> getNoteByID(long id)
    {
        return notesRepository.findById(id);
    }

    public void updateNote(Notes notes)
    {
        long currentMilliSeconds = System.currentTimeMillis();
        Date now = new Date(currentMilliSeconds);
        notes.setLastModifiedTime(now);
        notesRepository.save(notes);
    }

    public void setIsFavourite(long notesId)
    {
        Optional<Notes> notes = notesRepository.findById(notesId);
        notes.get().setFavourite(true);
        notesRepository.save(notes.get());

    }

    public void removeFavourite(long notesId)
    {
        Optional<Notes> notes = notesRepository.findById(notesId);
        notes.get().setFavourite(false);
        notesRepository.save(notes.get());
    }


}
