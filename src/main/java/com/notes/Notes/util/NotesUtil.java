package com.notes.Notes.util;

import com.notes.Notes.model.Notes;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class NotesUtil {


    public static JSONArray convertNotesListToJson(List<Notes> notesList)
    {
        JSONArray notesArray = new JSONArray();
        notesList.stream().forEach(notes -> {
            JSONObject notesObject = new JSONObject();
            notesObject.put("id",notes.getId());
            notesObject.put("title",notes.getTitle());
            notesObject.put("content",notes.getContent());
            notesObject.put("addedTime",notes.getAddedTime());
            notesObject.put("modifiedTime",notes.getLastModifiedTime());
            notesObject.put("favourite",notes.getFavourite());
            notesArray.put(notesObject);
        });
        return notesArray;
    }

}
