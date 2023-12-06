package com.example.note;

import java.io.Serializable;

public class dbclass implements Serializable {
    private String title;
    private String note;
    private int id;

    public dbclass()
    {

    }
    public dbclass(String title, String note, int id) {
        this.title = title;
        this.note = note;
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
