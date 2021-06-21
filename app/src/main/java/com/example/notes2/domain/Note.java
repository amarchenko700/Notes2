package com.example.notes2.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Note implements Serializable {
    private String nameNote;
    private String desscriptionNote;
    private Date dateCreate;

    public Note(String nameNote, String desscriptionNote) {
        this.nameNote = nameNote;
        this.desscriptionNote = desscriptionNote;
        this.dateCreate = new Date();
    }

    public String getNameNote() {
        return nameNote;
    }

    public String getDesscriptionNote() {
        return desscriptionNote;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }
}
