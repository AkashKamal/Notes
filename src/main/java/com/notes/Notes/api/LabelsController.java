package com.notes.Notes.api;


import com.notes.Notes.model.Labels;
import com.notes.Notes.service.LabelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins={ "http://localhost:3000", "http://localhost:4200" })
public class LabelsController {

    @Autowired
    private LabelsService labelsService;

    @PostMapping("/api/v1/addLabel")
    public String addLabel(@RequestBody Labels labels)
    {
        labelsService.addLabel(labels);
        return "success";
    }

    @PostMapping("/api/v1/updateLabel")
    public String updateLabel(@RequestBody Labels labels)
    {
        labelsService.updateLabel(labels);
        return "success";
    }

    @GetMapping("/api/v1/getAllLabels")
    public List<Labels> getAllLabels()
    {
        return labelsService.getAlLabels();
    }

    @PostMapping("/api/v1/addNoteToLabel")
    public void addNoteToLabel(@RequestParam(name="labelId", required = true) long labelId, @RequestParam(name="notesId", required = true) long notesId) throws Exception
    {
        labelsService.addNoteToLabel(labelId,notesId);
    }

    @GetMapping("/api/v1/label/getNotes")
    public String getNotesOfLabel(@RequestParam(name="labelId", required = true) long labelId) throws Exception{
        return labelsService.getNotesOfLabels(labelId);
    }

    @DeleteMapping("/api/v1/label/deleteLabel")
    public String delteLabel(@RequestParam(name="labelId", required = true)long labelId)
    {
        labelsService.deleteLabel(labelId);
        return "success";
    }

}
