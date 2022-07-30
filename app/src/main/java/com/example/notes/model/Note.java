package com.example.notes.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "note_name")
    public String noteName;

    @ColumnInfo(name = "note_body")
    public String noteBody;

    @ColumnInfo(name = "note_date")
    public String noteDate;
}
