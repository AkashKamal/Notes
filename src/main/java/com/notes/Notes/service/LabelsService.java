package com.notes.Notes.service;

import com.notes.Notes.Security.services.UserDetailsImpl;
import com.notes.Notes.exception.APIException;
import com.notes.Notes.exception.ErrorCode;
import com.notes.Notes.model.Labels;
import com.notes.Notes.model.Notes;
import com.notes.Notes.repository.LabelsRepository;
import com.notes.Notes.util.NotesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LabelsService {

    @Autowired
    LabelsRepository labelsRepository;

    @Autowired
    NotesService notesService;

    public void addLabel(Labels labels)
    {
        long currentMilliSeconds = System.currentTimeMillis();
        Date now = new Date(currentMilliSeconds);
        labels.setAddedTime(now);
        labels.setModifiedTime(now);
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        labels.setUser(user.getUser());
        Labels addedLabel = labelsRepository.save(labels);
    }

    public void updateLabel(Labels label)
    {
        long currentMilliSeconds = System.currentTimeMillis();
        Date now = new Date(currentMilliSeconds);
        label.setModifiedTime(now);
        labelsRepository.save(label);
    }

    public void deleteLabel(long labelId)
    {
        labelsRepository.deleteById(labelId);
    }

    public List<Labels> getAlLabels()
    {
        UserDetailsImpl user = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return labelsRepository.findLabelsByUser(user.getUser());
    }

    public Labels getLabelById(long labelId) throws Exception
    {
        Optional<Labels> label= labelsRepository.findById(labelId);
        if(label.isPresent())
        {
            return label.get();
        }
        else{
            throw new APIException(ErrorCode.LABEL_NOT_FOUND);
        }
    }

    public void addNoteToLabel(long labelId,long notesId) throws Exception
    {
        Labels label = getLabelById(labelId);
        Notes note =  notesService.getNoteByID(notesId);
        label.getNotesList().add(note);
        labelsRepository.save(label);
    }

    public String getNotesOfLabels(long labelId) throws Exception
    {
        Labels label = getLabelById(labelId);
        List<Notes> notesList = label.getNotesList();
        return NotesUtil.convertNotesListToJson(notesList).toString();
    }

}
