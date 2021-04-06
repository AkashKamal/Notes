package com.notes.Notes.repository;

import com.notes.Notes.model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository <Notes, Long>{
}
