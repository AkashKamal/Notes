package com.notes.Notes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "Notes")
public class Notes {
    @Id
    @SequenceGenerator(
            name = "notes_sequence",
            sequenceName = "notes_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "notes_sequence"
    )
    private long id;
    private String title;
    private String content;
    private boolean isFavourite;
    private Date addedTime;
    private Date lastModifiedTime;

    public Notes()
    {

    }

    public Notes( @JsonProperty("title") String title, @JsonProperty("content") String content) {
        this.title = title;
        this.content = content;
        this.isFavourite = false;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public Date getAddedTime() {
        return addedTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }
}
