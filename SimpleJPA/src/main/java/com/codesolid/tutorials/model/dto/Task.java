package com.codesolid.tutorials.model.dto;

import com.sun.org.apache.xpath.internal.operations.Bool;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.*;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(columnDefinition = "varchar(250)")
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
