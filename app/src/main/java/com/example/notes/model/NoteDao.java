package com.example.notes.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM note")
    List<Note> getAllUsers();

    @Insert
    void insertNote(Note... Notes);

    @Delete
    void delete(Note note);

    @Update
    void updateNote(Note note);

}
