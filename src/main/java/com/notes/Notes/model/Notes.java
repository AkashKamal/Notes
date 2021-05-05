package com.notes.Notes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

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
    @ManyToOne
    @JoinColumn(name="user_id")
    private Users user;



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

    public void setAddedTime (Date addedTime)
    {
        this.addedTime = addedTime;
    }
    public void setLastModifiedTime(Date lastModifiedTime)
    {
        this.lastModifiedTime = lastModifiedTime;
    }

    public long getUserId() {
        return user.getId();
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public boolean getFavourite() {
        return isFavourite;
    }

    public void setUser(Users user)
    {
        this.user = user;
    }

    public Users getUser() {
        return user;
    }



}
