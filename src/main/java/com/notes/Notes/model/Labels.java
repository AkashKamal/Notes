package com.notes.Notes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity(name = "Labels")
public class Labels {


    @Id
    @SequenceGenerator(
            name = "label_sequence",
            sequenceName = "label_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "label_sequence"
    )
    long id;
    String labelName;
    @OneToOne
    @JoinColumn(name="user_id")
    private Users user;
    Date addedTime;
    Date modifiedTime;
    @ManyToMany
    private List<Notes> notesList;

    public  Labels(){

    }

    public Labels(@JsonProperty("labelName") String labelName)
    {
        this.labelName = labelName;
    }

    public long getId() {
        return id;
    }


    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Date getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Date addedTime) {
        this.addedTime = addedTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public List<Notes> getNotesList() {
        return notesList;
    }

    public void setNotesList(List<Notes> notesList) {
        this.notesList = notesList;
    }
}
