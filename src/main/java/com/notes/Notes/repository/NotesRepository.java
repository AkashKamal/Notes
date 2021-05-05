package com.notes.Notes.repository;

import com.notes.Notes.model.Notes;
import com.notes.Notes.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotesRepository extends JpaRepository <Notes, Long>{
    List<Notes> findNotesByUser (Users user);
    List<Notes> findNotesByFavourite (Users users, boolean isFavourite);
}
