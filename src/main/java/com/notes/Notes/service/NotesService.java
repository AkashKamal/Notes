package com.notes.Notes.service;

import com.notes.Notes.Security.services.UserDetailsImpl;
import com.notes.Notes.model.Notes;
import com.notes.Notes.repository.NotesRepository;
import com.notes.Notes.util.NotesUtil;
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
        notes.setLastModifiedTime(now);
        UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        notes.setUser(userDetails.getUser());
        notesRepository.save(notes);
    }

    public String getAllNotes()
    {
        UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Notes> notesList = notesRepository.findNotesByUser(userDetails.getUser());
        return NotesUtil.convertNotesListToJson(notesList).toString();
    }

    public String getAllFavouriteNotes()
    {
        UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Notes> notesList = notesRepository.findNotesByFavourite(userDetails.getUser(), Boolean.TRUE);
        return NotesUtil.convertNotesListToJson(notesList).toString();
    }

    public Optional<Notes> getNoteByID(long id)
    {
        return notesRepository.findById(id);
    }

    public void updateNote(Notes notes)
    {
        try {
            long currentMilliSeconds = System.currentTimeMillis();
            Date now = new Date(currentMilliSeconds);
            notes.setLastModifiedTime(now);
            UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            notes.setUser(userDetails.getUser());
            notesRepository.save(notes);
        }
        catch (Exception e)
        {

        }
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
