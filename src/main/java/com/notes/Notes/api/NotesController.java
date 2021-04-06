package com.notes.Notes.api;

import com.notes.Notes.model.Notes;
import com.notes.Notes.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins={ "http://localhost:3000", "http://localhost:4200" })
public class NotesController {

    @Autowired
    private NotesService notesService;


    @PostMapping("/api/v1/addNote")
    public String addNotes(@RequestBody Notes notes)
    {
        notesService.addNotes(notes);
        return "success";
    }

    @GetMapping("/allNotes")
    public List<Notes> getAllNotes()
    {
        return notesService.getAllNotes();
    }

    @GetMapping("/notes/{notesId}")
    public Optional<Notes> getNoteById(@PathVariable long notesId)
    {
        return notesService.getNoteByID(notesId);
    }
}