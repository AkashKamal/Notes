package com.notes.Notes.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.sql.Date;

@Entity(name = "Users")
public class Users {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private long  id;
    private String email;
    private String password;
    private Date addedTime;

    public Users(){

    }


    public Users(@JsonProperty("email") String email, @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public Date getAddedTime() {
        return addedTime;
    }

    public long getId()
    {
        return id;
    }

    public String getPassword()
    {
        return password;
    }
}
