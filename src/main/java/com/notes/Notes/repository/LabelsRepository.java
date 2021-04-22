package com.notes.Notes.repository;

import com.notes.Notes.model.Labels;
import com.notes.Notes.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabelsRepository  extends JpaRepository<Labels, Long> {
    List<Labels> findLabelsByUser(Users user);
}
