package com.notes.Notes.service;

import com.notes.Notes.Security.services.UserDetailsImpl;
import com.notes.Notes.model.Labels;
import com.notes.Notes.model.Users;
import com.notes.Notes.repository.LabelsRepository;
import com.notes.Notes.repository.UsersRepository;
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

    public void addLabel(Labels labels)
    {
        long currentMilliSeconds = System.currentTimeMillis();
        Date now = new Date(currentMilliSeconds);
        labels.setAddedTime(now);
        labels.setModifiedTime(now);
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        labels.setUser(user.getUser());
        labelsRepository.save(labels);
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
}
