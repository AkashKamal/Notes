package com.notes.Notes.service;

import com.notes.Notes.Security.services.UserDetailsImpl;
import com.notes.Notes.model.Notes;
import com.notes.Notes.repository.NotesRepository;
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

    public void addNotes(Notes notes)
    {
        long currentMilliSeconds = System.currentTimeMillis();
        Date now = new Date(currentMilliSeconds);
        notes.setAddedTime(now);
        UserDetailsImpl user = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        notes.setUserId(user.getid());
        notesRepository.save(notes);
    }

    public List<Notes> getAllNotes()
    {
        UserDetailsImpl user = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return notesRepository.findNotesByUserId(user.getid());
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


}
