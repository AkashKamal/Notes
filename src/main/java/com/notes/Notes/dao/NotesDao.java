package com.notes.Notes.dao;

import com.notes.Notes.model.Notes;
import com.notes.Notes.repository.NotesRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class NotesDao {

   private static Map<Long,Notes> notesMap = new HashMap<>();

    public int addNote(long userId, Notes notes)
    {
        notesMap.put(notes.getId(),notes);
        return 0;
    }

    public int deleteNote(int userId, int notesId)
    {
        return 0;
    }

    public int updateNote(int userId, Notes notes)
    {
        return 0;
    }

    public Notes getNote(int noteID)
    {
        return notesMap.get(noteID);
    }
    public List<Notes> getAllNotesofAnUser(int userID)
    {
        List<Notes> allNotes = new ArrayList<>();
        for(int i=0;i<notesMap.size();i++)
        {
            allNotes.add(notesMap.get(i));
        }
        return allNotes;
    }
}
