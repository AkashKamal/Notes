package com.notes.Notes.repository;

import com.notes.Notes.model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotesRepository extends JpaRepository <Notes, Long>{
    List<Notes> findNotesByUserId (long userId);
}
