package com.notes.Notes.service;

import com.notes.Notes.model.Notes;
import com.notes.Notes.repository.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotesService {

    @Autowired
    NotesRepository notesRepository;

    public void addNotes(Notes notes)
    {
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

}
