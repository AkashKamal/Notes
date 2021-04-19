package com.notes.Notes.service;

import com.notes.Notes.model.Notes;
import com.notes.Notes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        notesRepository.save(notes);
    }

    public List<Notes> getAllNotes()
    {

        return notesRepository.findAll();
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
