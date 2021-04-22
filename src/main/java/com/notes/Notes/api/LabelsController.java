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

    @PostMapping("/api/v1/deleteLabel")
    public String deleteLabel(@RequestBody long labelId)
    {
        labelsService.deleteLabel(labelId);
        return "success";
    }

    @GetMapping("/api/v1/getAllLabels")
    public List<Labels> getAllLabels()
    {
        return labelsService.getAlLabels();
    }
}
