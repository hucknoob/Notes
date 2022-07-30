package com.example.notes.model;

import android.content.Context;

import androidx.room.ColumnInfo;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context){

        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,
                    "DB_NOTES")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
